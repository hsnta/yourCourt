import 'package:flutter/material.dart';
import 'package:frontend/shared/components/your_court_app_bar.dart';
import 'package:frontend/modules/dashboard/components/recommendations/components/recommendation_body.dart';

class Recommendations extends StatelessWidget {
  const Recommendations({super.key});

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      appBar: YourCourtAppBar(titleText: "Recommendations"),
      body: RecommendationsBody()
    );
  }
}
