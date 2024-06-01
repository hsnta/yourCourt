import 'package:flutter/material.dart';
import 'package:frontend/shared/components/your_court_app_bar.dart';
import 'package:frontend/modules/chats/components/chats_body.dart';

class Chats extends StatelessWidget {
  const Chats({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: YourCourtAppBar(
            titleText: "Chats",
            onEditPressed: _handleEdit,
            editIcon: Icons.edit_note),
        body: ChatsBody());
  }

  void _handleEdit() {
    print('Edit button pressed');
  }
}
