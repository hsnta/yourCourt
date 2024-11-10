import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/components/recommendations/recommendations.dart';
import 'stats/stats.dart';

class DashboardContents extends StatelessWidget {
  const DashboardContents({super.key});

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      body: Padding(
        padding: EdgeInsets.all(16.0),
        child: Column(
          children: [
            Expanded(
              flex: 100,
              child: Stats(),
            ),
            SizedBox(height: 10), // Spacing between the two sections
            Expanded(
              flex: 80,
              child: Recommendations(),
            ),
          ],
        ),
      ),
    );
  }
}

