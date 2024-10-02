import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:frontend/shared/models/login_data.dart';
import 'package:frontend/shared/models/registration_data.dart';
import 'package:frontend/shared/models/tokens_response.dart';
import 'package:frontend/shared/services/secure_storage_service.dart';

class AuthService extends ChangeNotifier {
  // TODO create interceptor with retry functionality
  // check how to apply retry to graphql requests (change all auth endpoints to graphql ?)
  String? _loggedInUser;
  String? get getLoggedInUser => _loggedInUser;
  final SecureStorageService _secureStorageService;

  final Dio unprotectedDio = Dio(BaseOptions(
      baseUrl: "http://localhost:8070/v1",
      responseType: ResponseType.json,
      contentType: "applicaton/json"));
  
  final Dio protectedEndpointsDio = Dio(BaseOptions(
      baseUrl: "http://localhost:8070/v1",
      responseType: ResponseType.json,
      contentType: "applicaton/json"));
  
  AuthService(this._secureStorageService) {
    protectedEndpointsDio.interceptors.add(RefreshTokenInterceptor())
  }

  void login(LoginData loginData) {
    const String path = "/auth/login";
    unprotectedDio.post(path, data: loginData.toJson()).then((resp) =>
        _secureStorageService.storeAuthData(tokenResponseFromJson(resp.toString())));
    _secureStorageService.getUsernameFromToken().then((val) => _loggedInUser = val);
  }

  void register(RegistrationData registrationData) {
    const String path = "/auth/register";
    unprotectedDio.post(path, data: registrationData.toJson()).then((resp) =>
        _secureStorageService.storeAuthData(tokenResponseFromJson(resp.toString())));
    _secureStorageService.getUsernameFromToken().then((val) => _loggedInUser = val);
  }

  void logout() {}

  void refreshToken() {}

}

class RefreshTokenInterceptor {}
