import 'package:flutter/material.dart';
import 'package:frontend/modules/page1/videoplayer/video_player_widget_state.dart';

class VideoPlayerWidget extends StatefulWidget {
  final String url;
  final String title;
  final String description;

  const VideoPlayerWidget({
    super.key,
    required this.url,
    required this.title,
    required this.description,
  });

  @override
  VideoPlayerWidgetState createState() => VideoPlayerWidgetState();
}
