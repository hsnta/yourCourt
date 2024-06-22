import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:frontend/modules/page3/input_results/court_part_button.dart';
import 'court_constants.dart' as court_constants;

class CourtView extends StatefulWidget {
  const CourtView({super.key});

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


  @override
  void dispose() {
    controller.dispose();
    animationController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    List<Widget> _buildChildWidgets() {
      final List<Widget> courtPartsWidgets = [];
      courtPartsWidgets.add(SvgPicture.asset("assets/basketball_no_vectors.svg",
          fit: BoxFit.cover));
      court_constants.partToPathMap.keys
          .map((name) => Positioned.fill(child: CourtPartButton(name)))
          .forEach((element) => courtPartsWidgets.add(element));
      return courtPartsWidgets;
    }

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
          // onTap: () => _dialogBuilder(context),
          child: InteractiveViewer(
              transformationController: controller,
              clipBehavior: Clip.none,
              panEnabled: true,
              minScale: 1,
              maxScale: 2.5,
              scaleEnabled: true,
              child: AspectRatio(
                  aspectRatio: 680 / 1024,
                  child: ClipRRect(
                      borderRadius: BorderRadius.circular(25),
                      child: Material(
                        child: Stack(children: _buildChildWidgets()),
                      ))))),
      ElevatedButton(
          onPressed: () => Navigator.of(context).pop(),
          child: Text("Finish entering the results"))
    ]);
  }
}
