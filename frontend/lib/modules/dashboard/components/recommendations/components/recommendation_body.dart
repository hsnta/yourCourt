import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/components/recommendations/components/recommendation_card.dart';

class RecommendationsBody extends StatefulWidget {
  const RecommendationsBody({super.key});

  @override
  _RecommendationsBodyState createState() => _RecommendationsBodyState();
}

class _RecommendationsBodyState extends State<RecommendationsBody> {
  List<Map<String, String>> cardData = [
    {"title": "Kobe Drill", "description": "10:00", "difficulty": "Beginner"},
    {"title": "Curry Drill", "description": "10:00", "difficulty": "Intermediate"},
    {"title": "D Rose Drill", "description": "10:00", "difficulty": "Advanced"},
    {"title": "LBJ Drill", "description": "10:00", "difficulty": "Intermediate"},
    {"title": "J Suggs Drill", "description": "10:00", "difficulty": "Beginner"},
    {"title": "Dame Drill", "description": "10:00", "difficulty": "Intermediate"},
  ];

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Expanded(
          child: ReorderableListView(
            padding: const EdgeInsets.all(16.0),
            children: cardData
                .map((data) => RecommendationCard(
              key: ValueKey(data["title"]),
              title: data["title"] ?? '',
              description: data["description"] ?? '',
              difficulty: data["difficulty"] ?? '',
            ))
                .toList(),
            onReorder: (int oldIndex, int newIndex) {
              setState(() {
                if (newIndex > oldIndex) {
                  newIndex -= 1;
                }
                final Map<String, String> item = cardData.removeAt(oldIndex);
                cardData.insert(newIndex, item);
              });
            },
          ),
        ),
        SizedBox(
          width: double.infinity,
          child: ElevatedButton(
            onPressed: () {
              // Add your action here
            },
            child: const Text(
              'CUSTOMIZE WORKOUT',
              style: TextStyle(
                fontSize: 16,
                fontWeight: FontWeight.bold,
                color: Colors.white,
              ),
            ),
          ),
        ),
      ],
    );
  }
}
