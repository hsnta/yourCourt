import 'package:flutter/material.dart';
import 'circular_stats.dart';

class Stats extends StatelessWidget {
  const Stats({super.key});

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        Container(
          decoration: const BoxDecoration(
            image: DecorationImage(
                image: AssetImage('assets/images/dashboard_background.png'),
                fit: BoxFit.fitWidth,
                alignment: Alignment.topCenter),
          ),
        ),
        Container(
          padding: const EdgeInsets.all(20.0),
          child: Container(
            padding: const EdgeInsets.all(15.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                const SizedBox(height: 1.0),
                const Text(
                  'Hi JAREL',
                  style: TextStyle(
                    color: Colors.blue,
                    fontSize: 24.0,
                    fontWeight: FontWeight.bold
                  ),
                ),
                const Row(
                  mainAxisAlignment: MainAxisAlignment.end,
                  children: [CircularStats(
                    countText: '10',
                    descriptionText: 'DAYS TILL TRYOUTS',
                  )]
                ),
                const SizedBox(height: 50.0),
                const Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
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
                // Progress bar
                Container(
                  height: 40,
                  child: LinearProgressIndicator(
                    backgroundColor: Colors.white,
                    valueColor: const AlwaysStoppedAnimation<Color>(Colors.blue),
                    value: 0.5, // Set the value of the progress bar (0.0 to 1.0)
                    borderRadius: BorderRadius.circular(100),
                  ),
                ),
                const SizedBox(height: 5.0),
                const Center(
                  child: Text(
                    "7,777 SHOTS LEFT KEEP GOING",
                    style: TextStyle(
                      fontSize: 20,
                    ),
                    textAlign: TextAlign.center,
                  ),
                )

              ],
            ),
          ),
        )
      ],
    );
  }
}




