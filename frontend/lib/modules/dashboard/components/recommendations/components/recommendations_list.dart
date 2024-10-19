import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/components/recommendations/components/recommendation_card.dart';

class RecommendationsList extends StatelessWidget {
  final List<Map<String, String>> cardData;
  final Function(int, int) onReorder;

  const RecommendationsList({
    super.key,
    required this.cardData,
    required this.onReorder,
  });

  @override
  Widget build(BuildContext context) {
    return ReorderableListView(
      padding: const EdgeInsets.all(16.0),
      onReorder: onReorder,
      children: cardData
          .map((data) => RecommendationCard(
        key: ValueKey(data["title"]),
        title: data["title"] ?? '',
        description: data["description"] ?? '',
        difficulty: data["difficulty"] ?? '',
      ))
          .toList(),
    );
  }
}
