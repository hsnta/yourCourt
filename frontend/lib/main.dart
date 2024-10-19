import 'package:flutter/material.dart';
import 'package:frontend/shared/services/app_service.dart';
import 'package:frontend/shared/services/secure_storage_service.dart';
import 'package:provider/provider.dart';
import 'core/app.dart';

void main() {
  runApp(
    MultiProvider(
      providers: [
        // Singleton service: SecureStorageService
        Provider<SecureStorageService>(
          create: (context) => SecureStorageService(),
        ),

        // Singleton ChangeNotifier: AuthService, depends on SecureStorageService
        ChangeNotifierProxyProvider<SecureStorageService, AppService>(
            create: (context) => AppService(
                  Provider.of<SecureStorageService>(context, listen: false),
                ),
            update: (context, secureStorageService, authService) =>
                authService!),
      ],
      child: const MyApp(),
    ),
  );
}
