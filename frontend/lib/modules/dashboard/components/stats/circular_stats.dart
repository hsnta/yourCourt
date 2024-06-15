import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/components/stats/circular_count_indicator.dart';
import 'package:frontend/modules/dashboard/components/stats/count_description_box.dart';

class CircularStats extends StatelessWidget {
  final String countText;
  final String descriptionText;

  const CircularStats({
    super.key,
    required this.countText,
    required this.descriptionText,
  });

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 140,
      child: Stack(
        alignment: Alignment.topCenter,
        children: [
          CircularCountIndicator(countText: countText),
          Positioned(
            top: 80, // Adjust this value to control the overlap
            child: CountDescriptionBox(descriptionText: descriptionText),
          ),
        ],
      ),
    );
  }
}
