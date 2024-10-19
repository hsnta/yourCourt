import 'package:flutter/material.dart';

class ChatSearchBar extends StatelessWidget {
  final ValueChanged<String> onChanged;

  const ChatSearchBar({required this.onChanged, super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Container(
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(20.0),
          color: Colors.black,
        ),
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 8.0),
          child: TextField(
            decoration: const InputDecoration(
              hintText: 'Search',
              border: InputBorder.none,
              prefixIcon: Icon(Icons.search),
            ),
            onChanged: onChanged,
          ),
        ),
      ),
    );
  }
}
