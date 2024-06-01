import 'package:flutter/material.dart';
import 'package:frontend/modules/chats/services/chat_service.dart';

class ChatsBody extends StatefulWidget {
  @override
  _ChatsBodyState createState() => _ChatsBodyState();
}

class _ChatsBodyState extends State<ChatsBody> {
  late ChatService graphQLService;
  List<Map<String, String>> users = [];
  bool isLoading = true;

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
        isLoading = false;
      });
    } catch (e) {
      setState(() {
        isLoading = false;
        ScaffoldMessenger.of(context).showSnackBar(const SnackBar(
          content: Text("Failed to fetch data", style: TextStyle(color: Colors.white)),
          backgroundColor: Colors.red,
        ));
      });
      // Handle error accordingly

    }
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Padding(
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
                onChanged: (value) {
                  // Perform search logic here
                },
              ),
            ),
          ),
        ),
        const Divider(
          color: Colors.grey,
          height: 1,
        ),
        Expanded(
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
                  },
                ),
        ),
      ],
    );
  }
}
