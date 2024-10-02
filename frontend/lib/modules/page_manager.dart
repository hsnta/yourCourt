import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/dashboard.dart';
import 'package:frontend/modules/page1/page1.dart';
import 'package:frontend/modules/page2/page2.dart';
import 'package:frontend/modules/chats/chats.dart';
import 'package:frontend/modules/page3/input_results/current_drill_page.dart';

final Map<int, Widget Function()> pageMap = {
  0: () => const Page1(),
  1: () => const Page2(),
  2: () => const Dashboard(),
  3: () => const Chats(),
  // 4: () => const Page5(),
  4: () => const CurrentDrillPage(),
};

Widget getCurrentPage(int currentPage) {
  return pageMap[currentPage]!();
}
