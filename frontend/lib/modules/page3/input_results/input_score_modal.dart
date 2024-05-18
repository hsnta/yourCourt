import 'package:flutter/material.dart';
import 'dart:math';

class InputScoreModal extends StatefulWidget {
  const InputScoreModal({super.key});

  @override
  State<StatefulWidget> createState() => _InputScoreModalState();
}

class _InputScoreModalState extends State<InputScoreModal> {
  final myController = TextEditingController(text: '0');
  int score = 0;

  void _onScoreChange() {
    setState(() {
      score = myController.text == "" ? 0 : int.parse(myController.text);
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
    });
  }

  @override
  void initState() {
    super.initState();

    // Start listening to changes.
    myController.addListener(_onScoreChange);
  }

  @override
  void dispose() {
    // Clean up the controller when the widget is disposed.
    myController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return Column(
      children: [
        Text("Left Wing", style: theme.textTheme.headlineLarge),
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
    );
  }
}
