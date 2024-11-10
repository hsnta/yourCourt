import 'package:flutter/material.dart';

class ProgressBar extends StatelessWidget {
  final int currentStage;
  final List<String> stages;
  final ValueChanged<int> onStageChanged;

  const ProgressBar({
    Key? key,
    required this.currentStage,
    required this.stages,
    required this.onStageChanged,
  }) : assert(stages.length == 4),
        super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Slider(
          value: currentStage.toDouble(),
          min: 1,
          max: 4,
          divisions: 3,
          onChanged: (value) {
            onStageChanged(value.round());
          },
          activeColor: Colors.blue,
          inactiveColor: Colors.grey,
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: stages.asMap().entries.map((entry) {
            String stageName = entry.value;

            return Column(
              children: [
                const SizedBox(height: 4),
                Text(
                  stageName,
                  style: const TextStyle(
                    fontSize: 12,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ],
            );
          }).toList(),
        ),
      ],
    );
  }
}
