import 'package:flutter/material.dart';

class DateLabelWidget extends StatelessWidget {
  final String date;

  const DateLabelWidget({
    required this.date,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 100, // Adjust the height as needed
      width: 50,
      padding: EdgeInsets.symmetric(vertical: 10, horizontal: 5),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text(date.split(' ')[0], style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold)),
          Text(date.split(' ')[1], style: TextStyle(fontSize: 14)),
        ],
      ),
    );
  }
}
