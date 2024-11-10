import 'package:flutter/material.dart';

import 'components/diary_screen.dart';

class Page2 extends StatelessWidget {
  const Page2({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: DiaryScreen(),
    );
  }
}