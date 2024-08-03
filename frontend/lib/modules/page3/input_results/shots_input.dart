import 'package:flutter/material.dart';
import 'dart:math';

class ShotsInput extends StatefulWidget {
  final int score;
  final int maxShots;
  final ValueSetter<int> setScoreOnCourt;

  const ShotsInput(
      {super.key,
      required this.score,
      required this.maxShots,
      required this.setScoreOnCourt});

  @override
  State<StatefulWidget> createState() => _ShotsInputState();
}

class _ShotsInputState extends State<ShotsInput> {
  late int score;

  @override
  void initState() {
    super.initState();
    score = widget.score;
  }

  void _changeScore(bool increment) {
    setState(() {
      if (!increment) {
        score = max(0, --score);
      } else {
        score = min(widget.maxShots, ++score);
      }
      // widget.setScoreOnCourt(score);
    });
  }

  @override
  Widget build(BuildContext context) {
    return PopScope(
        canPop: true,
        onPopInvoked: (bool didPop) {
          print(didPop);
          if (didPop) {
            widget.setScoreOnCourt(score);
          }
        },
        child: Row(mainAxisAlignment: MainAxisAlignment.center, children: [
          IconButton(
            onPressed: () => _changeScore(false),
            icon: const Icon(Icons.arrow_back_ios),
            iconSize: 40,
            color: null,
          ),
          Text(
            textAlign: TextAlign.center,
            score.toString(),
            style: TextStyle(
              color: Colors.white,
              fontSize: 35,
              fontWeight: FontWeight.bold,
              fontFamily: 'NBA',
            ),
          ),
          IconButton(
            onPressed: () => _changeScore(true),
            iconSize: 40,
            icon: const Icon(Icons.arrow_forward_ios),
            color: null,
          )
        ]));
  }
}
