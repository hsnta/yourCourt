import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/components/customize_workout/components/workout_body.dart';
import 'package:frontend/shared/components/your_court_app_bar.dart';

class CustomizeWorkout extends StatelessWidget {
  const CustomizeWorkout({super.key});

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
        appBar: YourCourtAppBar(
          titleText: "Customize Workouts",
        ),
        body: WorkoutBody());
  }
}

class CustomizeWorkoutPage extends StatelessWidget {
  const CustomizeWorkoutPage({super.key});

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
        body: CustomizeWorkout()
    );
  }
}
