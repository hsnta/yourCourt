import 'package:flutter/material.dart';
import 'package:frontend/shared/services/app_service.dart';
import 'package:provider/provider.dart';

class GreetingText extends StatelessWidget {
  const GreetingText({super.key});

  @override
  Widget build(BuildContext context) {
    var appService = Provider.of<AppService>(context);
    return Text(
      'Hi ${appService.getLoggedInUser}',
      style: const TextStyle(
        color: Colors.blue,
        fontSize: 24.0,
        fontWeight: FontWeight.bold,
      ),
    );
  }
}
