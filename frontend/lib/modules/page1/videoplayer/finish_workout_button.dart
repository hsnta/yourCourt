import 'package:flutter/material.dart';
import 'package:frontend/modules/page1/input_results/court_constants.dart';
import 'package:frontend/modules/page1/input_results/court_view.dart';

class FinishWorkoutButton extends StatelessWidget {
  FinishWorkoutButton({super.key});

  final Set<String> disabledPartsSet = {
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
    return Positioned(
      top: 16,
      left: 16,
      child: ElevatedButton(
        onPressed: () => _dialogBuilder(context),
        style: ElevatedButton.styleFrom(
          backgroundColor: Colors.blue,
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(8.0),
          ),
          padding: const EdgeInsets.symmetric(horizontal: 16.0, vertical: 8.0),
        ),
        child: const Text(
          'Finish',
          style: TextStyle(color: Colors.white),
        ),
      ),
    );
  }
}
