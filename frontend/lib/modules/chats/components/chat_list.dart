import 'package:flutter/material.dart';
import 'chat_user_tile.dart';

class ChatList extends StatelessWidget {
  final List<Map<String, String>> users;
  final bool isLoading;

  const ChatList({required this.users, required this.isLoading, super.key});

  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: isLoading
          ? const Center(child: CircularProgressIndicator())
          : ListView.separated(
              itemCount: users.length + 1,
              separatorBuilder: (context, index) => const Divider(
                color: Colors.grey,
                height: 1,
              ),
              itemBuilder: (context, index) {
                if (index == users.length) {
                  return Container();
                }
                final user = users[index];
                return ChatUserTile(user: user);
              },
            ),
    );
  }
}
