import 'package:flutter/material.dart';
import 'package:frontend/modules/page3/input_results/court_constants.dart';

import 'court_view.dart';

class CurrentDrillPage extends StatefulWidget {
  const CurrentDrillPage({super.key});

  @override
  State<CurrentDrillPage> createState() => _CurrentDrillPageState();
}

class _CurrentDrillPageState extends State<CurrentDrillPage> {
  Set<String> disabledPartsSet = {
    "Left Corner 2 points",
    "Left Corner 3 points",
    "Left Wing 2 points",
  };
  Future<void> _dialogBuilder(BuildContext context) {
    return showDialog<void>(
        context: context,
        builder: (BuildContext context) {
          return CourtView(getMockList());
        });
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          const Text("Current Drill page"),
          ElevatedButton(
            child: const Text("Tap To Enter Shot Makes"),
            onPressed: () => _dialogBuilder(context),
          )
        ],
      ),
    );
  }
}
