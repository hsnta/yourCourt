import 'package:flutter/material.dart';
import 'bottom_navigation/bottom_navigation.dart';

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Your Court',
      theme: ThemeData(
        appBarTheme: const AppBarTheme(elevation: 1),
        iconTheme: const IconThemeData(color: Colors.white),
        brightness: Brightness.dark,
        cardTheme: const CardTheme(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.all(
              Radius.circular(16.0),
            ),
          ),
        ),
      ),
      home: const BottomNavigation(),
    );
  }
}
