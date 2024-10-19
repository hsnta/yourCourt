import 'package:flutter/material.dart';

class ShotsCounter extends StatelessWidget {
  final int numerator;
  final int denominator;

  const ShotsCounter({
    super.key,
    required this.numerator,
    required this.denominator,
  });

  @override
  Widget build(BuildContext context) {
    return IntrinsicWidth(
        child: IntrinsicHeight(
            child: Container(
                color: Colors.transparent,
                child: RichText(
                  text: TextSpan(
                    text: '$numerator',
                    style: const TextStyle(
                      color: Colors.white,
                      fontSize: 25,
                      fontWeight: FontWeight.bold,
                      fontFamily: 'NBA',
                    ),
                    children: [
                      WidgetSpan(
                          child: Transform.translate(
                        offset: const Offset(-8, 25),
                        child: const Text(
                          '/',
                          style: TextStyle(
                            color: Colors.white,
                            fontSize: 35,
                            fontWeight: FontWeight.bold,
                            fontFamily: 'NBA',
                          ),
                        ),
                      )),
                      WidgetSpan(
                        child: Transform.translate(
                            offset: const Offset(-15, 40),
                            child: Text(
                              '$denominator',
                              style: const TextStyle(
                                color: Colors.white,
                                fontSize: 35,
                                fontWeight: FontWeight.bold,
                                fontFamily: 'NBA',
                              ),
                            )),
                      ),
                    ],
                  ),
                ))));
  }
}
