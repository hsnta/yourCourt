import 'package:flutter/material.dart';
import 'package:frontend/modules/page1/videoplayer/video_player_controls.dart';
import 'package:frontend/modules/page1/videoplayer/video_player_widget.dart';
import 'package:video_player/video_player.dart';

class VideoPlayerWidgetState extends State<VideoPlayerWidget> {
  late VideoPlayerController _videoPlayerController;
  bool _isPlaying = false;
  Duration? _timeRemaining;
  double _sliderValue = 0.0;

  @override
  void initState() {
    super.initState();
    _videoPlayerController =
        VideoPlayerController.networkUrl(Uri.parse(widget.url))
          ..initialize().then((_) {
            setState(() {});
          });

    _videoPlayerController.setLooping(true);

    _videoPlayerController.addListener(() {
      if (_videoPlayerController.value.isPlaying != _isPlaying) {
        setState(() {
          _isPlaying = _videoPlayerController.value.isPlaying;
        });
      }
      setState(() {
        _timeRemaining = _videoPlayerController.value.duration -
            _videoPlayerController.value.position;
        _sliderValue =
            _videoPlayerController.value.position.inMilliseconds.toDouble();
      });
    });

    _videoPlayerController.play();
    _isPlaying = true;
  }

  @override
  void dispose() {
    _videoPlayerController.dispose();
    super.dispose();
  }

  void _togglePlayPause() {
    setState(() {
      if (_videoPlayerController.value.isPlaying) {
        _videoPlayerController.pause();
        _isPlaying = false;
      } else {
        _videoPlayerController.play();
        _isPlaying = true;
      }
    });
  }

  void _onSliderChanged(double value) {
    setState(() {
      _sliderValue = value;
      Duration newPosition = Duration(milliseconds: value.toInt());
      _videoPlayerController.seekTo(newPosition);
    });
  }

  void _onSliderChangeEnd(double value) {
    _videoPlayerController.seekTo(Duration(milliseconds: value.toInt()));
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: _togglePlayPause,
      child: Stack(
        alignment: Alignment.center,
        children: [
          AspectRatio(
            aspectRatio: _videoPlayerController.value.aspectRatio,
            child: VideoPlayer(_videoPlayerController),
          ),
          VideoPlayerControls(
            controller: _videoPlayerController,
            isPlaying: _isPlaying,
            sliderValue: _sliderValue,
            onSliderChanged: _onSliderChanged,
            onSliderChangeEnd: _onSliderChangeEnd,
            onPlayPauseToggle: _togglePlayPause,
            timeRemaining: _timeRemaining,
            title: widget.title,
            description: widget.description,
          ),
        ],
      ),
    );
  }
}
