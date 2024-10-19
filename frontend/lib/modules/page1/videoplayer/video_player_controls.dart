import 'package:flutter/material.dart';
import 'package:frontend/modules/page1/videoplayer/finish_workout_button.dart';
import 'package:frontend/modules/page1/videoplayer/like_and_send_buttons.dart';
import 'package:frontend/modules/page1/videoplayer/remianing_time_display.dart';
import 'package:frontend/modules/page1/videoplayer/video-slider.dart';
import 'package:frontend/modules/page1/videoplayer/video_info.dart';
import 'package:video_player/video_player.dart';

class VideoPlayerControls extends StatelessWidget {
  final VideoPlayerController controller;
  final bool isPlaying;
  final double sliderValue;
  final ValueChanged<double> onSliderChanged;
  final ValueChanged<double> onSliderChangeEnd;
  final VoidCallback onPlayPauseToggle;
  final Duration? timeRemaining;
  final String title;
  final String description;

  const VideoPlayerControls(
      {required this.controller,
      required this.isPlaying,
      required this.sliderValue,
      required this.onSliderChanged,
      required this.onSliderChangeEnd,
      required this.onPlayPauseToggle,
      required this.timeRemaining,
      required this.title,
      required this.description,
      super.key});

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        FinishWorkoutButton(),
        RemainingTimeDisplay(timeRemaining: timeRemaining),
        Positioned(
          bottom: 0,
          left: 0,
          right: 0,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const LikeAndSendButtons(),
              VideoInfo(title: title, description: description),
              VideoSlider(
                sliderValue: sliderValue,
                maxSliderValue:
                    controller.value.duration.inMilliseconds.toDouble(),
                onSliderChanged: onSliderChanged,
                onSliderChangeEnd: onSliderChangeEnd,
              ),
            ],
          ),
        ),
      ],
    );
  }
}
