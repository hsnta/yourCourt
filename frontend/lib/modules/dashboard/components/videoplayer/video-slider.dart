import 'package:flutter/material.dart';

class VideoSlider extends StatelessWidget {
  final double sliderValue;
  final double maxSliderValue;
  final ValueChanged<double> onSliderChanged;
  final ValueChanged<double> onSliderChangeEnd;

  const VideoSlider({
    required this.sliderValue,
    required this.maxSliderValue,
    required this.onSliderChanged,
    required this.onSliderChangeEnd,
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return SliderTheme(
      data: SliderThemeData(
        trackHeight: 1,
        thumbShape: const RoundSliderThumbShape(enabledThumbRadius: 2.0),
        overlayShape: const RoundSliderOverlayShape(overlayRadius: 10.0),
        activeTrackColor: Colors.white,
        inactiveTrackColor: Colors.grey.withOpacity(0.5),
        thumbColor: Colors.white,
        overlayColor: Colors.white.withOpacity(0.3),
      ),
      child: Slider(
        min: 0.0,
        max: maxSliderValue,
        value: sliderValue,
        onChanged: onSliderChanged,
        onChangeEnd: onSliderChangeEnd,
      ),
    );
  }
}
