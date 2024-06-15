import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/components/recommendations/components/recommendations_list.dart';
import 'package:frontend/modules/dashboard/components/recommendations/components/customize_workout_button.dart';
import 'package:frontend/modules/dashboard/components/recommendations/services/recommendation_service.dart';

class RecommendationsBody extends StatefulWidget {
  const RecommendationsBody({super.key});

  @override
  _RecommendationsBodyState createState() => _RecommendationsBodyState();
}

class _RecommendationsBodyState extends State<RecommendationsBody> {
  late RecommendationService graphQLService;
  List<Map<String, String>> recommendations = [];
  bool isLoading = true;

  @override
  void initState() {
    super.initState();
    graphQLService = RecommendationService();
    _fetchRecommendations();
  }

  Future<void> _fetchRecommendations() async {
    try {
      final fetchedUsers = await graphQLService.fetchAllRecommendations();
      setState(() {
        recommendations = fetchedUsers;
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
      mainAxisAlignment: MainAxisAlignment.center,
      children:  [
        Expanded(
          child: isLoading
              ? const Center(child: CircularProgressIndicator())
              :RecommendationsList(
            cardData: recommendations,
            onReorder: (oldIndex, newIndex) {
              setState(() {
                if (newIndex > oldIndex) {
                  newIndex -= 1;
                }
                final Map<String, String> item = recommendations.removeAt(oldIndex);
                recommendations.insert(newIndex, item);
              });
            },
          ),
        ),
        CustomizeWorkoutButton(
          onPressed: () {
            // Add your action here
          },
        ),
      ],
    );
  }
}
