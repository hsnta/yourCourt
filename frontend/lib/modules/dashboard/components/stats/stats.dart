import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/components/stats/background_image.dart';
import 'package:frontend/modules/dashboard/components/stats/circular_stats.dart';
import 'package:frontend/modules/dashboard/components/stats/greeting_text.dart';
import 'package:frontend/modules/dashboard/components/stats/progress_bar.dart';
import 'package:frontend/modules/dashboard/components/stats/progress_text.dart';
import 'package:frontend/modules/dashboard/components/stats/stats_row.dart';

class Stats extends StatelessWidget {
  const Stats({super.key});

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        const BackgroundImage(),
        Container(
          padding: const EdgeInsets.all(20.0),
          child: Container(
            padding: const EdgeInsets.all(15.0),
            child: const Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                SizedBox(height: 1.0),
                GreetingText(),
                SizedBox(height: 20.0),
                Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [
                    CircularStats(
                      countText: '10',
                      descriptionText: 'DAYS TILL TRYOUTS',
                    ),
                  ],
                ),
                SizedBox(height: 50.0),
                StatsRow(
                  stats: [
                    CircularStats(
                      countText: '5',
                      descriptionText: 'DAYS OUT OF MONTH',
                    ),
                    CircularStats(
                      countText: '10',
                      descriptionText: 'WORKOUTS COMPLETED',
                    ),
                    CircularStats(
                      countText: '9',
                      descriptionText: 'AVG DRILLS PER DAY',
                    ),
                  ],
                ),
                SizedBox(height: 20.0),
                ProgressBar(value: 0.5),
                SizedBox(height: 5.0),
                ProgressText(),
              ],
            ),
          ),
        ),
      ],
    );
  }
}
