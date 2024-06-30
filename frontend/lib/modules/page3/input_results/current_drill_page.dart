import 'package:flutter/material.dart';

import 'court_view.dart';

class CurrentDrillPage extends StatefulWidget {
  const CurrentDrillPage({super.key});

  @override
  State<CurrentDrillPage> createState() => _CurrentDrillPageState();
}

class _CurrentDrillPageState extends State<CurrentDrillPage> {
  Future<void> _dialogBuilder(BuildContext context) {
    return showDialog<void>(
        context: context,
        builder: (BuildContext context) {
          return const CourtView();
        });
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          const Text("Current Drill page"),
          ElevatedButton(
            child: const Text("Tap To Enter Shot Makes"),
            onPressed: () => _dialogBuilder(context),
          )
        ],
      ),
    );
  }
}
