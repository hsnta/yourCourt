import 'package:flutter/material.dart';

class CircularStats extends StatelessWidget {
  final String countText;
  final String descriptionText;

  const CircularStats({
    Key? key,
    required this.countText,
    required this.descriptionText,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 140, // Set the height of the stack
      child: Stack(
        alignment: Alignment.topCenter, // Align everything to the top center
        children: [
          Container(
            alignment: Alignment.topCenter, // Align the circle and text container to the top center
            child: CircleAvatar(
              radius: 50,
              backgroundColor: Colors.white,
              child: Text(
                countText,
                style: TextStyle(
                  color: Colors.blue,
                  fontWeight: FontWeight.bold,
                  fontSize: 40,
                ),
              ),
            ),
          ),
          Positioned(
            top: 80, // Adjust this value to control the overlap
            child: Container(
              width: 85,
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.circular(5),
              ),
              padding: EdgeInsets.all(5),
              child: Text(
                descriptionText,
                maxLines: 2,
                overflow: TextOverflow.ellipsis,
                style: TextStyle(
                  color: Colors.black,
                  fontSize: 12,
                  fontWeight: FontWeight.bold
                ),
                textAlign: TextAlign.center,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
