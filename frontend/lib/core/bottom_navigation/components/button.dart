import 'package:flutter/material.dart';

class BottomNavigationButton extends StatelessWidget {
  final BottomNavigationBarItemData item;
  final int currentPage;
  final Function(int) onPressed;

  const BottomNavigationButton(this.item, this.currentPage, this.onPressed,
      {super.key});

  @override
  Widget build(BuildContext context) {
    return IconButton(
      onPressed: () {
        onPressed(item.index);
      },
      icon: Icon(
        item.icon,
        color: currentPage == item.index ? Colors.blueAccent : null,
      ),
    );
  }
}

class BottomNavigationBarItemData {
  final IconData icon;
  final int index;

  BottomNavigationBarItemData(this.icon, this.index);
}
