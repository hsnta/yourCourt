import 'package:flutter/material.dart';
import 'package:graphql_flutter/graphql_flutter.dart';

class ChatService {
  final HttpLink httpLink = HttpLink('http://localhost:3000/graphql');

  late ValueNotifier<GraphQLClient> client;

  ChatService() {
    client = ValueNotifier(
      GraphQLClient(
        cache: GraphQLCache(),
        link: httpLink,
      ),
    );
  }

  Future<List<Map<String, String>>> fetchAllChats() async {
    const String query = r'''
      query {
        allChats {
          message
          User {
            name
            profilePic
          }
        }
      }
    ''';

    final QueryResult result = await client.value.query(
      QueryOptions(
        document: gql(query),
      ),
    );

    if (result.hasException) {
      throw Exception(result.exception.toString());
    }

    final List users = result.data?['allChats'] ?? [];
    return users.map((user) => {
      'name': user['User']['name'] as String,
      'imagePath': user['User']['profilePic'] as String,
      'message': user['message'] as String,
    }).toList();
  }
}
