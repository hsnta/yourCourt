import 'package:flutter/material.dart';

import 'input_score_modal.dart';

class CourtView extends StatefulWidget {
  const CourtView();

  @override
  State<CourtView> createState() => _CourtViewState();
}

class _CourtViewState extends State<CourtView>
    with SingleTickerProviderStateMixin {
  late TransformationController controller;
  Animation<Matrix4>? animation;
  late AnimationController animationController;
  TapDownDetails? tapDownDetails;
  _CourtViewState();

  @override
  void initState() {
    super.initState();
    animationController =
        AnimationController(vsync: this, duration: Duration(milliseconds: 300))
          ..addListener(() {
            controller.value = animation!.value;
          });
    controller = TransformationController();
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
            child: InputScoreModal(),
          ));
        });
  }

  @override
  void dispose() {
    controller.dispose();
    animationController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Column(children: [
      GestureDetector(
          onDoubleTapDown: (details) => tapDownDetails = details,
          onDoubleTap: () {
            final position = tapDownDetails!.localPosition;
            const scale = 2.5;
            final x = -position.dx * (scale - 1);
            final y = -position.dy * (scale - 1);
            final zoomed = Matrix4.identity()
              ..translate(x, y)
              ..scale(scale);
            final end =
                controller.value.isIdentity() ? zoomed : Matrix4.identity();

            animation = Matrix4Tween(
              begin: controller.value,
              end: end,
            ).animate(CurvedAnimation(
                parent: animationController, curve: Curves.easeIn));

            animationController.forward(from: 0);
          },
          onTap: () => _dialogBuilder(context),
          child: InteractiveViewer(
              transformationController: controller,
              clipBehavior: Clip.none,
              panEnabled: true,
              minScale: 1,
              maxScale: 2.5,
              scaleEnabled: true,
              child: AspectRatio(
                  aspectRatio: 0.6,
                  child: ClipRRect(
                    borderRadius: BorderRadius.circular(25),
                    child:
                        Image.asset("assets/basketball.jpg", fit: BoxFit.cover),
                  )))),
      ElevatedButton(
          onPressed: () => Navigator.of(context).pop(),
          child: Text("Finish entering the results"))
    ]);
  }
}
