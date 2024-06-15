import 'package:flutter/material.dart';

class GreetingText extends StatelessWidget {
  const GreetingText({super.key});

  @override
  Widget build(BuildContext context) {
    return const Text(
      'Hi JAREL',
      style: TextStyle(
        color: Colors.blue,
        fontSize: 24.0,
        fontWeight: FontWeight.bold,
      ),
    );
  }
}
