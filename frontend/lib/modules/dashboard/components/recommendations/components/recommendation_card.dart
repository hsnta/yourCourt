import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/components/videoplayer/video_list_page.dart';
import 'package:frontend/shared/components/your_court_app_bar.dart';

class RecommendationCard extends StatelessWidget {
  final String title;
  final String description;
  final String difficulty;

  const RecommendationCard({
    super.key,
    required this.title,
    required this.description,
    required this.difficulty,
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 4.0,
      margin: const EdgeInsets.symmetric(vertical: 8.0),
      color: Colors.white, // Set background color to white
      child: Row(
        children: [
          Expanded(
            child: _RecommendationContent(
              title: title,
              description: description,
            ),
          ),
          _Tag(difficulty: difficulty),
          const _DragButton(),
        ],
      ),
    );
  }
}

class _RecommendationContent extends StatelessWidget {
  final String title;
  final String description;

  const _RecommendationContent({
    super.key,
    required this.title,
    required this.description,
  });

  @override
  Widget build(BuildContext context) {
    return ListTile(
      leading: const Icon(Icons.image), // Placeholder for the image
      title: Text(
        title,
        style: const TextStyle(
          fontSize: 20,
          fontWeight: FontWeight.bold,
          color: Colors.black,
        ),
      ),
      subtitle: Text(
        description,
        style: const TextStyle(color: Colors.black),
      ),
    );
  }
}

class _Tag extends StatelessWidget {
  final String difficulty;

  const _Tag({
    Key? key,
    required this.difficulty,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    Color tagColor = Colors.green; // Default color for beginner

    // Determine tag color based on difficulty
    switch (difficulty.toLowerCase()) {
      case 'intermediate':
        tagColor = Colors.blue;
        break;
      case 'advanced':
        tagColor = Colors.red;
        break;
      default:
        tagColor = Colors.green;
    }

    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 10, vertical: 0),
      margin: const EdgeInsets.only(bottom: 51),
      decoration: BoxDecoration(
        color: tagColor,
        borderRadius: BorderRadius.circular(100.0),
      ),
      child: Center(
        child: Text(
          difficulty,
          style: const TextStyle(
            color: Colors.white,
            fontWeight: FontWeight.bold,
          ),
          textAlign: TextAlign.center,
        ),
      ),
    );
  }
}

class _DragButton extends StatelessWidget {
  const _DragButton({super.key});

  @override
  Widget build(BuildContext context) {
    return IconButton(
      icon: const Icon(Icons.drag_indicator, color: Colors.black),
      onPressed: () {
        print('object');
        Navigator.push(
          context,
          MaterialPageRoute(
            builder: (context) => const Video(),
          ),
        );
        // Handle drag button press
      },
    );
  }
}

class Video extends StatelessWidget {
  const Video({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const YourCourtAppBar(
      titleText: "",
      ),
      body: VideoPage(),
    );
  }
}

class VideoPage extends StatelessWidget {
  final List<Map<String, String>> videos = [
    {
      'url':
      'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4',
      'title': 'Kobe Drill',
      'description': 'Athletic Playmaking Shooting Guard Layup Drills'
    },
    {
      'url':
      'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4',
      'title': 'Curry Drill',
      'description': 'Another short animated film featuring animals.'
    },
    {
      'url':
      'https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4',
      'title': 'D Rose Drill',
      'description': 'Yet another short animated film featuring animals.'
    },
    {
      'url':
      'https://www.exit109.com/~dnn/clips/RW20seconds_1.mp4',
      'title': 'LBJ Drill',
      'description': 'Yet another short animated film featuring animals.'
    },
  ];

  VideoPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: VideoListPage(videos: videos),
    );
  }
}
