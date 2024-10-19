import 'package:flutter/material.dart';
import 'package:frontend/modules/page1/input_results/court_parts_clipper.dart';
import 'package:frontend/modules/page1/input_results/shots_counter.dart';
import 'package:frontend/modules/page1/input_results/shots_input.dart';

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
  late AnimationController _animationController;

  @override
  void initState() {
    super.initState();
    _animationController = AnimationController(
      duration: const Duration(milliseconds: 250),
      vsync: this,
    );
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
    setState(() {
      _inputMode = true;
    });
    final RenderBox renderBox = context.findRenderObject() as RenderBox;
    final position = renderBox.localToGlobal(Offset.zero);
    widget.blockOtherPartsCallback(widget.partName);
    _dialogBuilder(context, position);
    _animationController.forward();
  }

  Future<void> _dialogBuilder(BuildContext context, Offset globalPosition) {
    final bounds = widget.path.getBounds();
    return showDialog<bool>(
        context: context,
        builder: (BuildContext context) {
          return LayoutBuilder(builder: (context, constraints) {
            return Stack(children: [
              Positioned(
                  top: globalPosition.dy + bounds.top,
                  left: bounds.left,
                  child: Container(
                      height: bounds.height,
                      width: bounds.width,
                      color: Colors.transparent,
                      child: Align(
                        alignment: Alignment.center,
                        child: OverflowBox(
                            minWidth: 0.0,
                            minHeight: 0.0,
                            maxWidth: double.infinity,
                            maxHeight: double.infinity,
                            child: Transform.translate(
                                offset: const Offset(4, -26),
                                child: ShotsInput(
                                    score: score,
                                    maxShots: widget.shotsNeeded,
                                    setScoreOnCourt: setScore))),
                      ))),
            ]);
          });
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
                child: Opacity(
                    opacity: 0.4,
                    child: Container(color: Colors.black26.withOpacity(0.8)))),
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
