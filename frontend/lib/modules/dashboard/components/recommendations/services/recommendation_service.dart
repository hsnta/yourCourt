import 'package:flutter/material.dart';
import 'package:graphql_flutter/graphql_flutter.dart';

class RecommendationService {
  final HttpLink httpLink = HttpLink('http://localhost:3000/graphql');

  late ValueNotifier<GraphQLClient> client;

  RecommendationService() {
    client = ValueNotifier(
      GraphQLClient(
        cache: GraphQLCache(),
        link: httpLink,
      ),
    );
  }

  Future<List<Map<String, String>>> fetchAllRecommendations() async {
    const String query = r'''
      query {
        allRecommendations {
          drillName
          difficulty
          drillImagePath
          durationOfDrill
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

    final List recommendations = result.data?['allRecommendations'] ?? [];
    return recommendations
        .map((recommendation) => {
              'title': recommendation['drillName'] as String,
              'description': recommendation['durationOfDrill'] as String,
              'difficulty': recommendation['difficulty'] as String,
              'imagePath': recommendation['drillImagePath'] as String,
            })
        .toList();
  }
}
