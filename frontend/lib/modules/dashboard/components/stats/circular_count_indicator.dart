import 'package:flutter/material.dart';

class CircularCountIndicator extends StatelessWidget {
  final String countText;

  const CircularCountIndicator({super.key, required this.countText});

  @override
  Widget build(BuildContext context) {
    return CircleAvatar(
      radius: 50,
      backgroundColor: Colors.white,
      child: Text(
        countText,
        style: const TextStyle(
          color: Colors.blue,
          fontWeight: FontWeight.bold,
          fontSize: 40,
        ),
      ),
    );
  }
}
