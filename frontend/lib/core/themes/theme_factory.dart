import 'package:flutter/material.dart';
import 'package:frontend/core/helpers/platform_helper.dart';
import 'android_theme.dart';
import 'ios_theme.dart';

class ThemeFactory {
  static ThemeData createTheme(BuildContext context) {
    bool isIOS = PlatformHelper.isIOS(context);
    return isIOS ? iosTheme : androidTheme;
  }
}
