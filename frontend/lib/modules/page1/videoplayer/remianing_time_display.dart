import 'package:flutter/material.dart';

class RemainingTimeDisplay extends StatelessWidget {
  final Duration? timeRemaining;

  const RemainingTimeDisplay({required this.timeRemaining, super.key});

  @override
  Widget build(BuildContext context) {
    return Positioned(
      top: 16,
      right: 16,
      child: Container(
        padding: const EdgeInsets.all(8.0),
        decoration: BoxDecoration(
          color: Colors.red,
          borderRadius: BorderRadius.circular(8.0),
        ),
        child: Text(
          timeRemaining != null
              ? '${timeRemaining!.inMinutes}:${(timeRemaining!.inSeconds % 60).toString().padLeft(2, '0')}'
              : '',
          style: const TextStyle(color: Colors.white),
        ),
      ),
    );
  }
}