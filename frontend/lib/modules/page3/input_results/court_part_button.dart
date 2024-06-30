import 'package:flutter/material.dart';
import 'package:frontend/modules/page3/input_results/court_parts_clipper.dart';
import 'package:frontend/modules/page3/input_results/input_score_modal.dart';

class CourtPartButton extends StatefulWidget {
  final String partName;

  const CourtPartButton(this.partName, {super.key});

  @override
  State<CourtPartButton> createState() => _CourtPartButtonState();
}

class _CourtPartButtonState extends State<CourtPartButton>
    with SingleTickerProviderStateMixin {
  bool _isPressed = false;
  late AnimationController _animationController;

  @override
  void initState() {
    super.initState();
    _animationController = AnimationController(
      duration: const Duration(milliseconds: 200),
      vsync: this,
    );
  }

  @override
  void dispose() {
    _animationController.dispose();
    super.dispose();
  }

  Future<void> _dialogBuilder(BuildContext context) {
    return showDialog<void>(
        context: context,
        builder: (BuildContext context) {
          final appTheme = Theme.of(context);

          return Center(
              child: Container(
            height: (MediaQuery.of(context).size.height * 0.25),
            width: (MediaQuery.of(context).size.width * 0.75),
            decoration: BoxDecoration(
              borderRadius: const BorderRadius.all(
                Radius.circular(16.0),
              ),
              color: appTheme.cardColor,
            ),
            child: InputScoreModal(widget.partName),
          ));
        });
  }

  void _handleTapDown(TapDownDetails details) {
    setState(() {
      _isPressed = true;
    });
    _animationController.forward();
  }

  void _handleTapUp(TapUpDetails details) {
    setState(() {
      _isPressed = false;
    });
    _animationController.reverse();
    // Add your onPressed logic here
    print('Button Pressed');
  }

  void _handleTapCancel() {
    setState(() {
      _isPressed = false;
    });
    _animationController.reverse();
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTapDown: _handleTapDown,
      onTapUp: _handleTapUp,
      onTapCancel: _handleTapCancel,
      onTap: () => _dialogBuilder(context),
      child: ClipPath(
          clipper: CourtPartsClipper(widget.partName),
          child: LayoutBuilder(builder: (context, constraints) {
            final Size size = constraints.biggest;
            final path = CourtPartsClipper(widget.partName).getClip(size);
            final bounds = path.getBounds();

            return AnimatedContainer(
                duration: const Duration(milliseconds: 50),
                curve: Curves.easeOut,
                decoration: BoxDecoration(
                  border: Border.all(color: Colors.black, width: 3),
                  boxShadow: _isPressed
                      ? []
                      : [
                          BoxShadow(
                            color: Colors.black26.withOpacity(0.2),
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
                  Positioned(
                    left: bounds.left + (bounds.width / 2) - 10,
                    top: bounds.top + (bounds.height / 2) - 20,
                    child: const Text(
                      '10',
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 35,
                        fontWeight: FontWeight.bold,
                        fontFamily: 'NBA',
                      ),
                    ),
                  ),
                ]));
          })),
    );
  }
}
