import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/components/videoplayer/video_player_widget.dart';

class VideoListPage extends StatelessWidget {
  final List<Map<String, String>> videos;

  const VideoListPage({super.key, required this.videos});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: PageView.builder(
        scrollDirection: Axis.vertical,
        itemCount: videos.length,
        itemBuilder: (context, index) {
          return VideoPlayerWidget(
            url: videos[index]['url']!,
            title: videos[index]['title']!,
            description: videos[index]['description']!,
          );
        },
      ),
    );
  }
}
