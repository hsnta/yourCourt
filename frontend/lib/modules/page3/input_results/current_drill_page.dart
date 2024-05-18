import 'package:flutter/material.dart';

import 'court_view.dart';

class CurrentDrillPage extends StatefulWidget {
  @override
  State<CurrentDrillPage> createState() => _CurrentDrillPageState();
}

class _CurrentDrillPageState extends State<CurrentDrillPage> {

  Future<void> _dialogBuilder(BuildContext context) {
    return showDialog<void>(
        context: context,
        builder: (BuildContext context) {
          return CourtView();
        });
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text("Current Drill page"),
          ElevatedButton(
            child: Text("Input results"),
            onPressed: () => _dialogBuilder(context),
          )
        ],
      ),
    );
  }
}