import 'package:flutter/material.dart';
import 'package:frontend/shared/components/constants.dart';
import 'package:graphql_flutter/graphql_flutter.dart';

enum DrillType {
  TWO_BALL_DRILLS,
  CONE_DRILLS,
  CROSSOVERS,
  CHANGE_OF_SPEED,
  CHANGE_OF_DIRECTION,
  CATCH_AND_SHOT,
  FIVE_STAR_DRILL,
  DRIBBLE_PULL_UPS,
  LEFT_HAND_LAYUPS,
  RIGHT_HAND_LAYUPS,
  CONTACT_FINISHES,
  FLOATERS,
  PICK_N_ROLL,
  DOWN_HILL_DRIVES,
  SHOTS_OFF_OF_SCREENS,
  SPRINTS,
  HILL_RUNS,
  SAND_PIT,
  UPPER_BODY,
  LOWER_BODY,
  JUMPING,
  EXPLOSIVENESS,
  LADDER_DRILLS,
  DEFENSIVE_SLIDES,
  WALKING_STRETCHES,
  STATIC_STRETCHES,
}

class WorkoutService {
  final HttpLink httpLink = HttpLink(
    '${Constants.endpoint}/graphql',
  );

  late ValueNotifier<GraphQLClient> client;

  WorkoutService() {
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
        allCustomizeDrills{
          drillType,
          drillDifficulty,
          categories,
          tags,
          description,
          drillName
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

    final List users = result.data?['allCustomizeDrills'] ?? [];
    var usersMapped = users
        .map((user) => {
              'drillType': user['drillType'] as String,
              'drillName': user['drillName'] as String,
              'drillDifficulty': user['drillDifficulty'] as String,
              'categories': (user['categories'] as List).join(', '),
              'tags': (user['tags'] as List).join(', '),
              'description': user['description'] as String,
             })
        .toList();
    return usersMapped;
  }

  Future<void> createWorkoutByDrillsSelection(
      String userId, List<DrillType> drillTypes) async {
    const String mutation = r'''
      mutation CreateWorkoutByDrillsSelection($userId: String!, $drillTypes: [DrillType!]!) {
        createWorkoutByDrillsSelection(customDrills: {
          userId: $userId,
          drillTypes: $drillTypes
        }) {
          userId
        }
      }
    ''';

    final MutationOptions options = MutationOptions(
      document: gql(mutation),
      variables: <String, dynamic>{
        'userId': userId,
        'drillTypes': drillTypes.map((dt) => drillTypeToString(dt)).toList(),
      },
    );

    final QueryResult result = await client.value.mutate(options);

    if (result.hasException) {
      throw Exception(result.exception.toString());
    }

    // You can handle the result here if needed
    print('Mutation result: ${result.data}');
  }

  String drillTypeToString(DrillType drillType) {
    return drillType.toString().split('.').last;
  }
}
