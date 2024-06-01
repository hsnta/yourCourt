import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/dashboard.dart';
import 'package:frontend/modules/page2/page2.dart';
import 'package:frontend/modules/chats/chats.dart';
import 'package:frontend/modules/page5/page5.dart';

import 'Page1/Page1.dart';

final Map<int, Widget Function()> pageMap = {
  0: () => const Page1(),
  1: () => const Page2(),
  2: () => const Dashboard(),
  3: () => const Chats(),
  4: () => const Page5(),
};

Widget getCurrentPage(int currentPage) {
  return pageMap[currentPage]!();
}