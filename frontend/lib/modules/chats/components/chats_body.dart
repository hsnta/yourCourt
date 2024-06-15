import 'package:flutter/material.dart';
import 'package:frontend/modules/chats/components/chat_search_bar.dart';
import 'package:frontend/modules/chats/components/chat_list.dart';
import 'package:frontend/shared/components/error_snackbar.dart';
import 'package:frontend/modules/chats/services/chat_service.dart';

class ChatsBody extends StatefulWidget {
  const ChatsBody({super.key});
  @override
  ChatsBodyState createState() => ChatsBodyState();
}

class ChatsBodyState extends State<ChatsBody> {
  late ChatService graphQLService;
  List<Map<String, String>> users = [];
  List<Map<String, String>> filteredUsers = [];
  bool isLoading = true;
  String searchQuery = '';

  @override
  void initState() {
    super.initState();
    graphQLService = ChatService();
    _fetchUsers();
  }

  Future<void> _fetchUsers() async {
    try {
      final fetchedUsers = await graphQLService.fetchAllChats();
      setState(() {
        users = fetchedUsers;
        filteredUsers = fetchedUsers;
        isLoading = false;
      });
    } catch (e) {
      setState(() {
        isLoading = false;
      });
      ErrorSnackbar.show(context, "Failed to fetch data");
    }
  }

  void _filterUsers(String query) {
    setState(() {
      searchQuery = query;
      if (query.isEmpty) {
        filteredUsers = users;
      } else {
        filteredUsers = users.where((user) {
          final name = user['name']!.toLowerCase();
          final message = user['message']!.toLowerCase();
          final searchLower = query.toLowerCase();
          return name.contains(searchLower) || message.contains(searchLower);
        }).toList();
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        ChatSearchBar(
          onChanged: (value) {
            _filterUsers(value);
          },
        ),
        const Divider(
          color: Colors.grey,
          height: 1,
        ),
        ChatList(users: filteredUsers, isLoading: isLoading),
      ],
    );
  }
}
