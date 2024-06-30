import 'package:flutter/material.dart';
import 'dart:math';

class InputScoreModal extends StatefulWidget {
  final String partName;
  final int score;
  final ValueSetter<int> setScoreOnCourt;

  const InputScoreModal(this.partName, this.score, this.setScoreOnCourt,
      {super.key});

  @override
  State<StatefulWidget> createState() => _InputScoreModalState();
}

class _InputScoreModalState extends State<InputScoreModal> {
  final myController = TextEditingController(text: '0');
  late int score;

  void _onScoreChange() {
    setState(() {
      score = myController.text == "" ? 0 : int.parse(myController.text);
      widget.setScoreOnCourt(score);
    });
  }

  void _changeScore(bool increment) {
    setState(() {
      if (!increment) {
        score = max(0, --score);
      } else {
        ++score;
      }
      myController.text = score.toString();
      widget.setScoreOnCourt(score);
    });
  }

  @override
  void initState() {
    super.initState();
    score = widget.score;
    myController.text = score.toString();
    myController.addListener(_onScoreChange);
  }

  @override
  void dispose() {
    myController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return PopScope(
        canPop: true,
        onPopInvoked: (bool didPop) {
          if (didPop) {
            widget.setScoreOnCourt(score);
          }
        },
        child: Column(
          children: [
            Text(widget.partName, style: theme.textTheme.headlineMedium),
            Expanded(
                child: Center(
                    child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                Expanded(
                    child: IconButton(
                  onPressed: () => _changeScore(false),
                  icon: const Icon(Icons.arrow_back_ios),
                  color: null,
                )),
                Expanded(
                    child: Material(
                        child: TextField(
                  controller: myController,
                  textAlign: TextAlign.center,
                ))),
                Expanded(
                    child: IconButton(
                  onPressed: () => _changeScore(true),
                  icon: const Icon(Icons.arrow_forward_ios),
                  color: null,
                )),
              ],
            )))
          ],
        ));
  }
}
