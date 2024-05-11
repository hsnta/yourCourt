import 'package:flutter/material.dart';
import 'package:frontend/modules/page2/page2.dart';
import 'package:frontend/modules/page3/page3.dart';
import 'package:frontend/modules/page4/page4.dart';
import 'package:frontend/modules/page5/page5.dart';

import 'Page1/Page1.dart';

final Map<int, Widget Function()> pageMap = {
  0: () => Page1(),
  1: () => Page2(),
  2: () => Page3(),
  3: () => Page4(),
  4: () => Page5(),
};

Widget getCurrentPage(int currentPage) {
  return pageMap[currentPage]!();
}