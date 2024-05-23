import 'package:flutter/material.dart';
import 'package:frontend/modules/dashboard/components/dashboard_contents.dart';

class Dashboard extends StatelessWidget {
  const Dashboard({super.key});

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      body: DashboardContents(),
    );
  }
}