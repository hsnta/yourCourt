import 'package:flutter/material.dart';

class FinishWorkoutButton extends StatelessWidget {
  const FinishWorkoutButton({super.key});

  @override
  Widget build(BuildContext context) {
    return Positioned(
      top: 16,
      left: 16,
      child: ElevatedButton(
        onPressed: () {
          print("Finish Workout");
        },
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