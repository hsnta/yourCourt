import 'package:flutter/material.dart';

class ChatUserTile extends StatelessWidget {
  final Map<String, String> user;

  const ChatUserTile({required this.user, super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 8.0),
      child: ListTile(
        leading: Image.asset(
          user['imagePath']!,
          width: 50,
          height: 50,
        ),
        title: Text(
          user['name']!,
          style: const TextStyle(
            fontSize: 18,
            fontWeight: FontWeight.bold,
          ),
        ),
        subtitle: Text(user['message']!),
        onTap: () {
          print('${user['name']} tapped');
        },
      ),
    );
  }
}
