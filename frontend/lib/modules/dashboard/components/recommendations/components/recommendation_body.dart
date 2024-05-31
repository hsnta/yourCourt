import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/components/recommendations/components/recommendations_list.dart';
import 'package:frontend/modules/dashboard/components/recommendations/components/customize_workout_button.dart';

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
          child: RecommendationsList(
            cardData: cardData,
            onReorder: (oldIndex, newIndex) {
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
        CustomizeWorkoutButton(
          onPressed: () {
            // Add your action here
          },
        ),
      ],
    );
  }
}
