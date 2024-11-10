import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/components/customize_workout/components/wokout_tile.dart';

class WorkoutList extends StatelessWidget {
  final List<Map<String, String>> users;
  final bool isLoading;
  final ValueChanged<Map<String, bool>> onSelectionChanged;

  const WorkoutList({
    required this.users,
    required this.isLoading,
    required this.onSelectionChanged,
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: isLoading
          ? const Center(child: CircularProgressIndicator())
          : Padding(
        padding: const EdgeInsets.symmetric(horizontal: 16.0), // Horizontal padding
        child: ListView.builder(
          itemCount: users.length,
          itemBuilder: (context, index) {
            final user = users[index];
            return WorkoutTile(
              user: user,
              onSelected: (isSelected) {
                onSelectionChanged({user['drillType']!: isSelected});
              },
            );
          },
        ),
      ),
    );
  }
}
