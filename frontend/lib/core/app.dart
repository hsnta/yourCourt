import 'package:flutter/material.dart';
import 'package:frontend/core/themes/theme_factory.dart';
import 'bottom_navigation/bottom_navigation.dart';

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Your Court',
      theme: ThemeFactory.createTheme(context),
      home: const BottomNavigation(),
    );
  }
}
