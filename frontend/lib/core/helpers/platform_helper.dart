import 'package:flutter/material.dart';

class PlatformHelper {
  static bool isIOS(BuildContext context) {
    return Theme.of(context).platform == TargetPlatform.iOS;
  }
}