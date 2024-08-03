import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:frontend/modules/page3/input_results/court_part_button.dart';
import 'package:frontend/modules/page3/input_results/model/court_part_model.dart';
import 'court_constants.dart' as court_constants;

class CourtView extends StatefulWidget {
  final List<CourtPartModel> courtPartModels;
  const CourtView(this.courtPartModels, {super.key});

  @override
  State<CourtView> createState() => _CourtViewState();
}

class _CourtViewState extends State<CourtView>
    with SingleTickerProviderStateMixin {
  late TransformationController controller;
  Animation<Matrix4>? animation;
  late AnimationController animationController;
  TapDownDetails? tapDownDetails;
  String _currentInputWidget = "";
  _CourtViewState();

  @override
  void initState() {
    super.initState();
    animationController = AnimationController(
        vsync: this, duration: const Duration(milliseconds: 300))
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

  void blockNonFocusedWidgets(String curFocusedPartName) {
    print("(un)blocking others");
    setState(() {
      _currentInputWidget = curFocusedPartName;
    });
  }

  List<Widget> _buildChildWidgets(Size size) {
    final List<Widget> courtPartsWidgets = [];
    courtPartsWidgets.add(Opacity(
        opacity: 0.8,
        child: SvgPicture.asset(
          "assets/basketball_no_vectors.svg",
          fit: BoxFit.fill,
        )));
    widget.courtPartModels
        .map((model) => CourtPartButton(
            model.partName,
            court_constants.calculatePath(model.partName, size),
            model.isDisabled,
            model.colorThresholds[0],
            model.colorThresholds[1],
            model.shotsRequired,
            _currentInputWidget != "" && _currentInputWidget != model.partName,
            blockNonFocusedWidgets))
        .forEach((element) => courtPartsWidgets.add(element));
    return courtPartsWidgets;
  }

  @override
  Widget build(BuildContext context) {
    return Column(mainAxisAlignment: MainAxisAlignment.end, children: [
      Row(mainAxisAlignment: MainAxisAlignment.center, children: [
        Transform.scale(
            scaleX: 3,
            scaleY: 2,
            child: IconButton(
              icon: const Icon(Icons.keyboard_arrow_down),
              iconSize: 40.0,
              color: Colors.white,
              padding: const EdgeInsets.all(10.0),
              onPressed: () => Navigator.of(context).pop(),
              splashColor: Colors.transparent,
              highlightColor: Colors.transparent,
            )),
      ]),
      GestureDetector(
          onTapDown: (details) => print("tapped"),
          onDoubleTapDown: (details) => tapDownDetails = details,
          onDoubleTap: () {
            final position = tapDownDetails!.localPosition;
            const scale = 1.5;
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
          child: InteractiveViewer(
              transformationController: controller,
              clipBehavior: Clip.none,
              panEnabled: true,
              minScale: 1,
              maxScale: 1.5,
              scaleEnabled: true,
              child: AspectRatio(
                  aspectRatio: 580 / 580,
                  child: ClipRRect(
                      borderRadius: BorderRadius.circular(25),
                      child: LayoutBuilder(
                        builder: (context, constraints) => Stack(
                            children: _buildChildWidgets(constraints.biggest)),
                      ))))),
      Container(
          height: MediaQuery.sizeOf(context).height * 0.15,
          color: Colors.transparent)
      // FloatingActionButton(
      //   onPressed: () => Navigator.of(context).pop(),
      //   backgroundColor: Colors.green,
      //   shape: const CircleBorder(),
      //   child: const Icon(
      //     Icons.check,
      //     color: Colors.white,
      //   ),
      // ),
    ]);
  }
}
