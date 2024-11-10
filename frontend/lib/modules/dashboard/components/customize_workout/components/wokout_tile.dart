import 'package:flutter/material.dart';

class WorkoutTile extends StatefulWidget {
  final Map<String, String> user;
  final ValueChanged<bool> onSelected;

  const WorkoutTile({
    required this.user,
    required this.onSelected,
    super.key,
  });

  @override
  WorkoutTileState createState() => WorkoutTileState();
}

class WorkoutTileState extends State<WorkoutTile> {
  bool _isChecked = false;

  @override
  Widget build(BuildContext context) {
    final difficulty = widget.user['drillDifficulty'] ?? 'Unknown';

    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 8.0),
      child: Stack(
        children: [
          Container(
            padding: const EdgeInsets.symmetric(horizontal: 12.0), // Left and right padding
            decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.circular(12.0), // Adjust radius as needed
              boxShadow: [
                BoxShadow(
                  color: Colors.grey.withOpacity(0.3),
                  spreadRadius: 2,
                  blurRadius: 4,
                  offset: Offset(0, 2), // changes position of shadow
                ),
              ],
            ),
            child: ListTile(
              contentPadding: EdgeInsets.zero, // Remove default padding to apply custom padding
              leading: Checkbox(
                value: _isChecked,
                onChanged: (bool? value) {
                  setState(() {
                    _isChecked = value ?? false;
                    widget.onSelected(_isChecked);
                  });
                },
                checkColor: Colors.white, // Color of the checkmark
                activeColor: Colors.black, // Checkbox color
              ),
              title: Text(
                widget.user['drillName']!,
                style: const TextStyle(
                  fontSize: 20,
                  fontWeight: FontWeight.w900,
                  color: Colors.black, // Dark text color
                ),
              ),
              subtitle: Text(
                widget.user['description']!,
                style: const TextStyle(
                  color: Colors.black, // Dark subtitle color
                ),
              ),
              onTap: () {
                print('${widget.user['drillType']} tapped');
              },
            ),
          ),
          // The difficulty tag
          Positioned(
            top: 0,
            right: 0,
            child: Container(
              padding: const EdgeInsets.symmetric(horizontal: 8.0, vertical: 4.0),
              decoration: BoxDecoration(
                color: _getDifficultyColor(difficulty),
                borderRadius: BorderRadius.only(
                  bottomLeft: Radius.circular(8.0),
                  topRight: Radius.circular(8.0),
                ),
              ),
              child: Text(
                difficulty,
                style: const TextStyle(
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }

  Color _getDifficultyColor(String difficulty) {
    switch (difficulty.toLowerCase()) {
      case 'beginner':
        return Colors.green;
      case 'intermediate':
        return Colors.orange;
      case 'advanced':
        return Colors.red;
      case 'nba':
        return Colors.purple;
      default:
        return Colors.grey;
    }
  }
}
