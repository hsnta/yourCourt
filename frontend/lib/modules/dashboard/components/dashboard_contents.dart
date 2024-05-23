import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/components/recommendations/recommendations.dart';
import 'stats/stats.dart';

class DashboardContents extends StatelessWidget {
  const DashboardContents({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: PageView(
        scrollDirection: Axis.vertical,
        children: const [
          Stats(),
          Recommendations(),
        ],
      ),
    );
  }
}
