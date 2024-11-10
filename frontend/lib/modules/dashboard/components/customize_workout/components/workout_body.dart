import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/components/customize_workout/components/tags_filter.dart';
import 'package:frontend/modules/dashboard/components/customize_workout/components/workout_list.dart';
import 'package:frontend/modules/dashboard/components/customize_workout/components/workout_search_bar.dart';
import 'package:frontend/modules/dashboard/components/customize_workout/services/workout_service.dart';
import 'package:frontend/shared/components/error_snackbar.dart';
import 'package:frontend/modules/dashboard/components/customize_workout/components/progress_bar.dart'; // Import the ProgressBar widget

class WorkoutBody extends StatefulWidget {
  const WorkoutBody({Key? key}) : super(key: key);

  @override
  WorkoutBodyState createState() => WorkoutBodyState();
}

class WorkoutBodyState extends State<WorkoutBody> {
  late WorkoutService graphQLService;
  List<Map<String, String>> users = [];
  List<Map<String, String>> filteredUsers = [];
  Map<String, bool> selectedUsers = {};
  bool isLoading = true;
  String searchQuery = '';
  int _currentStage = 1; // Track the current progress stage (1 to 4)
  bool _showSearchBar = false;
  bool _showProgressBar = false;
  bool _showCategoryBar = false;
  bool _showTagBar = false;
  Set<String> _selectedTags = {};

  final List<String> _tags = ['Cardio', 'Strength', 'Flexibility', 'Endurance'];

  @override
  void initState() {
    super.initState();
    graphQLService = WorkoutService();
    _fetchUsers();
  }

  Future<void> _fetchUsers() async {
    try {
      final fetchedUsers = await graphQLService.fetchAllChats();
      setState(() {
        users = fetchedUsers;
        filteredUsers = fetchedUsers;
        isLoading = false;
      });
    } catch (e) {
      setState(() {
        isLoading = false;
      });
      Snackbar.showErrorNotification(context, "Failed to fetch data");
    }
  }

  void _filterUsers(String query) {
    setState(() {
      searchQuery = query;
      if (query.isEmpty) {
        filteredUsers = users;
      } else {
        filteredUsers = users.where((user) {
          final name = user['drillName']!.toLowerCase();
          final message = user['description']!.toLowerCase();
          final searchLower = query.toLowerCase();
          return name.contains(searchLower) || message.contains(searchLower);
        }).toList();
      }
    });
  }

  void _filterByDifficulty(int difficultyNumber) {
    setState(() {
      final difficulties = ['Beginner', 'Intermediate', 'Advanced', 'NBA'];
      final difficulty =  difficulties[difficultyNumber];
      searchQuery = difficulty;
      if (difficulty.isEmpty) {
        filteredUsers = users;
      } else {
        filteredUsers = users.where((user) {
          final drillDifficulty = user['drillDifficulty']!.toLowerCase();
          final searchLower = difficulty.toLowerCase();
          return drillDifficulty.contains(searchLower);
        }).toList();
      }
    });
  }

  void _handleTagSelected(String tag) {
    setState(() {
      print(tag);

      if (_selectedTags.contains(tag)) {
        _selectedTags.remove(tag);
      } else {
        _selectedTags.add(tag);
      }
      print(_selectedTags);
      _filterByDifficulty(_currentStage - 1); // Apply filters after tag selection
    });
  }

  void _handleSelectionChange(Map<String, bool> selection) {
    setState(() {
      selectedUsers.addAll(selection);
    });
  }

  DrillType stringToDrillType(String drillTypeString) {
    try {
      return DrillType.values.firstWhere(
            (e) => e.toString().split('.').last == drillTypeString,
      );
    } catch (e) {
      throw ArgumentError('Invalid drill type string: $drillTypeString');
    }
  }

  Future<void> _updateDrillTypes(
      String id, List<DrillType> newDrillTypes) async {
    try {
      await graphQLService.createWorkoutByDrillsSelection(id, newDrillTypes);
      Snackbar.showSuccessNotification(context, 'Drill types updated successfully!');
    } catch (e) {
      Snackbar.showErrorNotification(context, "Failed to save customized drills");
    }
  }

  void _sendSelectedUsers() {
    final selected = selectedUsers.entries
        .where((entry) => entry.value)
        .map((entry) => stringToDrillType(entry.key))
        .where((drillType) => drillType != null) // Ensure no null values
        .toList();
    _updateDrillTypes('Munir', selected);
    print('Selected Drills: $selected');
  }

  void _toggleSearchBar() {
    setState(() {
      _showSearchBar = !_showSearchBar;
      _showProgressBar = false;
      _showCategoryBar = false;
      _showTagBar = false;
    });
  }

  void _toggleProgressBar() {
    setState(() {
      _showSearchBar = false;
      _showProgressBar = !_showProgressBar;
      _showCategoryBar = false;
      _showTagBar = false;
    });
  }

  void _toggleCategoryBar() {
    setState(() {
      _showSearchBar = false;
      _showProgressBar = false;
      _showCategoryBar = !_showCategoryBar;
      _showTagBar = false;
    });
  }

  void _toggleTagBar() {
    setState(() {
      _showSearchBar = false;
      _showProgressBar = false;
      _showCategoryBar = false;
      _showTagBar = !_showTagBar;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          // Action Buttons
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            children: [
              IconButton(
                icon: const Icon(Icons.search),
                onPressed: _toggleSearchBar,
              ),
              IconButton(
                icon: const Icon(Icons.label),
                onPressed: _toggleCategoryBar, // You can implement filter by tag action here
              ),
              IconButton(
                icon: const Icon(Icons.filter_list),
                onPressed: _toggleProgressBar, // Shows the progress bar
              ),
              IconButton(
                icon: const Icon(Icons.tag),
                onPressed: _toggleTagBar, // You can implement filter by another tag action here
              ),
            ],
          ),
          const Divider(
            color: Colors.grey,
            height: 1,
          ),
          // Search Bar
          if (_showSearchBar)
            WorkoutSearchBar(
              onChanged: (value) {
                _filterUsers(value);
              },
            ),
          // Progress Bar
          if (_showProgressBar)
            Padding(
              padding: const EdgeInsets.symmetric(vertical: 8.0, horizontal: 16.0),
              child: ProgressBar(
                currentStage: _currentStage,
                stages: const ['Beginner', 'Intermediate', 'Advanced', 'NBA'], // Pass stage names
                onStageChanged: (newStage) {
                  setState(() {
                    _currentStage = newStage;
                    _filterByDifficulty(newStage - 1);
                    print(_currentStage);
                  });
                },
              ),
            ),
          if (_showTagBar)
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: TagsFilter(
                tags: _tags,
                selectedTags: _selectedTags,
                onTagSelected: _handleTagSelected,
              ),
            ),
          if (_showSearchBar || _showProgressBar || _showTagBar || _showCategoryBar)
            const Divider(
              color: Colors.grey,
              height: 1,
            ),
          WorkoutList(
            users: filteredUsers,
            isLoading: isLoading,
            onSelectionChanged: _handleSelectionChange,
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _sendSelectedUsers,
        child: const Icon(Icons.send),
      ),
    );
  }
}
