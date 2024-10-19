import 'package:flutter/material.dart';
import 'court_constants.dart' as court_constants;

class CourtPartsClipper extends CustomClipper<Path> {
  final String partName;

  CourtPartsClipper(this.partName);

  @override
  Path getClip(Size size) {
    return court_constants.calculatePath(partName, size);
  }

  @override
  bool shouldReclip(covariant CustomClipper<Path> oldClipper) {
    return true;
  }
}

class BorderPainter extends CustomPainter {
  String partName;

  BorderPainter(this.partName);
  @override
  void paint(Canvas canvas, Size size) {
    final paint = Paint()
      ..color = Colors.black
      ..strokeWidth = 3.0
      ..style = PaintingStyle.stroke
      ..maskFilter = const MaskFilter.blur(BlurStyle.normal, 2);
    canvas.drawPath(court_constants.calculatePath(partName, size), paint);
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) {
    return false;
  }
}

