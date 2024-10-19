import 'package:flutter/material.dart';

class ProgressBar extends StatelessWidget {
  final double value;

  const ProgressBar({super.key, required this.value});

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 40,
      child: LinearProgressIndicator(
        backgroundColor: Colors.white,
        valueColor: const AlwaysStoppedAnimation<Color>(Colors.blue),
        value: value, // Set the value of the progress bar (0.0 to 1.0)
        borderRadius: BorderRadius.circular(100),
      ),
    );
  }
}
