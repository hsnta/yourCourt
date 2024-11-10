import 'package:flutter/material.dart';

import 'date_label_widgets.dart';
import 'diary_entry_widgets.dart';

class DiaryScreen extends StatelessWidget {
  final List<Map<String, String>> entries = [
    {
      'date': '22nd Apr',
      'title': 'Thursday 22 April',
      'content': 'Up, and to the Office, where all the morning. At noon home to dinner, and C...'
    },
    {
      'date': '21st Apr',
      'title': 'Wednesday 21 April',
      'content': 'Up; and with my own coach as far as the Temple, and thence sent it to my co...'
    },
    {
      'date': '20th Apr',
      'title': 'Tuesday 20 April',
      'content': 'Up; and to the Office, and my wife abroad with Mary Batelier, with our own c...'
    },
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Daily Diary', style: TextStyle(color: Colors.blue),),
        actions: [
          IconButton(icon: Icon(Icons.filter_list), onPressed: () {}),
          IconButton(icon: Icon(Icons.sort), onPressed: () {}),
          IconButton(icon: Icon(Icons.search), onPressed: () {}),
        ],
      ),
      body: ListView.builder(
        itemCount: entries.length,
        itemBuilder: (context, index) {
          return Row(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              DateLabelWidget(date: entries[index]['date']!),
              Expanded(
                child: DiaryEntryWidget(
                  title: entries[index]['title']!,
                  content: entries[index]['content']!,
                ),
              ),
            ],
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          // Add new entry action
        },
        child: Icon(Icons.add),
      ),
    );
  }
}
