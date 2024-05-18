import 'package:flutter/material.dart';
import 'package:frontend/modules/page1/page1.dart';

import 'package:frontend/modules/page2/page2.dart';
import 'package:frontend/modules/page3/input_results/current_drill_page.dart';
import 'package:frontend/modules/page4/page4.dart';
import 'package:frontend/modules/page5/page5.dart';

final Map<int, Widget Function()> pageMap = {
  0: () => Page1(),
  1: () => Page2(),
  2: () => CurrentDrillPage(),
  3: () => Page4(),
  4: () => Page5(),
};

Widget getCurrentPage(int currentPage) {
  return pageMap[currentPage]!();
}
