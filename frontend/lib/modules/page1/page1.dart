import 'package:flutter/material.dart';
import '../dashboard/components/videoplayer/video_list_page.dart';

class Page1 extends StatelessWidget {
  const Page1({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: MyHomePage(),
    );
  }
}

class MyHomePage extends StatelessWidget {
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

  MyHomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: VideoListPage(videos: videos),
    );
  }
}
