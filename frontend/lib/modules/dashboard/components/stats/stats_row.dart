import 'package:flutter/material.dart';
import 'circular_stats.dart';

class StatsRow extends StatelessWidget {
  final List<CircularStats> stats;

  const StatsRow({super.key, required this.stats});

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: stats,
    );
  }
}
