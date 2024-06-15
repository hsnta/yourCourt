import 'package:flutter/material.dart';

class ProgressText extends StatelessWidget {
  const ProgressText({super.key});

  @override
  Widget build(BuildContext context) {
    return const Center(
      child: Text(
        "7,777 SHOTS LEFT KEEP GOING",
        style: TextStyle(
          fontSize: 20,
        ),
        textAlign: TextAlign.center,
      ),
    );
  }
}
