import 'package:flutter/material.dart';

class YourCourtAppBar extends StatelessWidget implements PreferredSizeWidget {
  final String titleText;
  final VoidCallback? onEditPressed;
  final IconData? editIcon;

  const YourCourtAppBar({
    super.key,
    required this.titleText,
    this.onEditPressed,
    this.editIcon,
  });

  @override
  Widget build(BuildContext context) {
    return AppBar(
      title: Text(
        titleText,
        style: const TextStyle(
          color: Colors.blue,
          fontWeight: FontWeight.w500,
          fontSize: 27,
        ),
      ),
      actions: onEditPressed != null
          ? [
        IconButton(
          icon: Icon(editIcon ?? Icons.edit),
          onPressed: onEditPressed,
        ),
      ]
          : null,
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);
}