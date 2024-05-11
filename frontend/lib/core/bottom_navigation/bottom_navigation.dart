import 'package:flutter/material.dart';
import '../../modules/page_manager.dart';
import 'components/button.dart';

class BottomNavigation extends StatefulWidget {
  const BottomNavigation({super.key});

  @override
  BottomNavigationState createState() => BottomNavigationState();
}

class BottomNavigationState extends State<BottomNavigation> {
  int currentPage = 2;

  final List<BottomNavigationBarItemData> bottomNavigationBarItems = [
    BottomNavigationBarItemData(Icons.note_add, 0),
    BottomNavigationBarItemData(Icons.location_on, 1),
    BottomNavigationBarItemData(Icons.sports_basketball_rounded, 2),
    BottomNavigationBarItemData(Icons.message_rounded, 3),
    BottomNavigationBarItemData(Icons.person, 4),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: getCurrentPage(currentPage),
      bottomNavigationBar: BottomAppBar(
        child: Row(
          children: [
            for (var item in bottomNavigationBarItems) ...[
              Spacer(
                key: Key(item.index.toString()),
              ),
              BottomNavigationButton(
                item,
                currentPage,
                onPageSelected,
              ),
            ],
             const Spacer(),
          ],
        ),
      ),
    );
  }

  void onPageSelected(int index) {
    setState(() {
      currentPage = index;
    });
  }
}
