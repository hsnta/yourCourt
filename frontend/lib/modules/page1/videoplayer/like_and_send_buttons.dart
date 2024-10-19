import 'package:flutter/material.dart';

class LikeAndSendButtons extends StatelessWidget {
  const LikeAndSendButtons({super.key});

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.end,
      children: [
        Column(
          crossAxisAlignment: CrossAxisAlignment.end,
          children: [
            IconButton(
              onPressed: () {
                // Handle like button action
                print("Clicked Like");
              },
              icon: const Icon(Icons.favorite_border, size: 30, color: Colors.white),
              color: Colors.transparent,
            ),
            IconButton(
              onPressed: () {
                print("Clicked Send");
                // Handle send button action
              },
              icon: const Icon(Icons.send, size: 30, color: Colors.white),
              color: Colors.transparent,
            ),
          ],
        ),
      ],
    );
  }
}