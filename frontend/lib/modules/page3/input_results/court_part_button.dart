import 'package:flutter/material.dart';
import 'package:frontend/modules/page3/input_results/court_parts_clipper.dart';
import 'package:frontend/modules/page3/input_results/shots_counter.dart';
import 'package:frontend/modules/page3/input_results/shots_input.dart';

class CourtPartButton extends StatefulWidget {
  final String partName;
  final bool isDisabled;
  final int lowThreshold;
  final int mediumTreshold;
  final int shotsNeeded;
  final Path path;
  final bool isNonFocused;
  final ValueSetter<String> blockOtherPartsCallback;

  const CourtPartButton(
      this.partName,
      this.path,
      this.isDisabled,
      this.lowThreshold,
      this.mediumTreshold,
      this.shotsNeeded,
      this.isNonFocused,
      this.blockOtherPartsCallback,
      {super.key});

  @override
  State<CourtPartButton> createState() => _CourtPartButtonState();
}

class _CourtPartButtonState extends State<CourtPartButton>
    with SingleTickerProviderStateMixin {
  bool _inputMode = false;
  int score = 0;
  final GlobalKey _childKey = GlobalKey();
  double _childWidth = 0;
  double _childHeight = 0;
  late AnimationController _animationController;

  @override
  void initState() {
    super.initState();
    // WidgetsBinding.instance
    //     .addPostFrameCallback((_) => _measureScoreCounterSize());
    _animationController = AnimationController(
      duration: const Duration(milliseconds: 250),
      vsync: this,
    );
  }

  void _measureScoreCounterSize() {
    final RenderBox? renderBox =
        _childKey.currentContext?.findRenderObject() as RenderBox?;
    if (renderBox != null) {
      setState(() {
        _childWidth = renderBox.size.width -
            (_inputMode ? 0 : 23); // moved to gorizontal offset
        _childHeight = renderBox.size.height +
            (_inputMode ? 0 : 55); // moved to vertical offset
      });
    }
  }

  @override
  void dispose() {
    _animationController.dispose();
    super.dispose();
  }

  void setScore(int score) {
    setState(() {
      _inputMode = false;
      this.score = score;
    });
    widget.blockOtherPartsCallback("");
    _animationController.reverse();
  }

  void _handleTapDown(TapDownDetails details, BuildContext context) {
    print("zone tap");
    setState(() {
      _inputMode = true;
    });
    final RenderBox renderBox = context.findRenderObject() as RenderBox;
    final position = renderBox.localToGlobal(Offset.zero);
    widget.blockOtherPartsCallback(widget.partName);
    WidgetsBinding.instance
        .addPostFrameCallback((_) => _measureScoreCounterSize());
    _dialogBuilder(context, position);
    _animationController.forward();
  }

  Future<void> _dialogBuilder(BuildContext context, Offset globalPosition) {
    final bounds = widget.path.getBounds();
    return showDialog<bool>(
        context: context,
        builder: (BuildContext context) {
          return Stack(children: [
            Positioned(
                left: globalPosition.dx +
                    bounds.left +
                    (bounds.width / 2) -
                    (_childWidth / 2),
                top: globalPosition.dy +
                    bounds.top +
                    (bounds.height / 2) -
                    (_childHeight / 2),
                child: Transform.translate(
                    offset: Offset(-58, -48),
                    child: ShotsInput(
                        key: _childKey,
                        score: score,
                        maxShots: widget.shotsNeeded,
                        setScoreOnCourt: setScore)))
          ]);
        });
  }

  Color _calculateColor() {
    if (score < widget.lowThreshold) {
      return Colors.blue.shade900.withOpacity(0.8);
    } else if (score < widget.mediumTreshold) {
      return Colors.blue.shade300.withOpacity(0.8);
    }
    return Colors.red.withOpacity(0.6);
  }

  @override
  Widget build(BuildContext context) {
    final bounds = widget.path.getBounds();
    if (widget.isDisabled || widget.isNonFocused) {
      return ClipPath(
          clipper: CourtPartsClipper(widget.partName),
          child: Stack(children: [
            Positioned.fill(
                child:
                    Opacity(opacity: 0.4, child: Container(color: Colors.red))),
            Positioned.fill(
                child: CustomPaint(
              painter: BorderPainter(widget.partName),
            )),
          ]));
    }
    return Stack(children: [
      GestureDetector(
        onTapDown: (details) => _handleTapDown(details, context),
        child: ClipPath(
            clipper: CourtPartsClipper(widget.partName),
            child: AnimatedContainer(
                duration: const Duration(milliseconds: 50),
                curve: Curves.easeOut,
                decoration: BoxDecoration(
                  border: Border.all(color: Colors.black, width: 3),
                  boxShadow: _inputMode
                      ? []
                      : [
                          BoxShadow(
                            color: _calculateColor(),
                            offset: Offset(4, 4),
                            blurRadius: 10,
                          ),
                        ],
                ),
                child: Stack(children: [
                  Positioned.fill(
                      child: CustomPaint(
                    painter: BorderPainter(widget.partName),
                  )),
                ]))),
      ),
      if (!_inputMode)
        Positioned(
            left: bounds.left,
            top: bounds.top,
            child: IgnorePointer(
                ignoring: true,
                child: Container(
                    width: bounds.width,
                    height: bounds.height,
                    color: Colors.transparent,
                    child: Align(
                        alignment: Alignment.center,
                        child: OverflowBox(
                            minWidth: 0.0,
                            minHeight: 0.0,
                            maxWidth: double.infinity,
                            maxHeight: double.infinity,
                            child: Transform.translate(
                                // offset: const Offset(15, -23),
                                offset: const Offset(13, -25),
                                child: ShotsCounter(
                                    numerator: score,
                                    denominator: widget.shotsNeeded))))))),
    ]);
  }
}
