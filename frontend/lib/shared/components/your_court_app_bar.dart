import 'package:flutter/material.dart';

class YourCourtAppBar extends StatelessWidget implements PreferredSizeWidget {
  final String titleText;

  const YourCourtAppBar({super.key, required this.titleText});

  @override
  Widget build(BuildContext context) {
    return AppBar(
      title: Text(titleText,
      style: const TextStyle(
        color: Colors.blue,
        fontWeight: FontWeight.w500,
        fontSize: 27,
      ),),
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);
}
