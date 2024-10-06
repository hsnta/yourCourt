import 'dart:async';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:frontend/shared/models/login_data.dart';
import 'package:frontend/shared/models/registration_data.dart';
import 'package:frontend/shared/models/tokens_response.dart';
import 'package:frontend/shared/services/secure_storage_service.dart';
import 'package:graphql_flutter/graphql_flutter.dart';
import 'package:jwt_decode/jwt_decode.dart';

class AppService extends ChangeNotifier {
  static const String baseUrl = "http://localhost:8070/v1";
  String? _loggedInUser;
  String? get getLoggedInUser => _loggedInUser;
  final SecureStorageService _secureStorageService;
  late Dio dio;
  late GraphQLClient graphQLClient;

  AppService(this._secureStorageService) {
    // this client is making requests without auth header
    dio = Dio(BaseOptions(
        baseUrl: baseUrl,
        responseType: ResponseType.json,
        contentType: "applicaton/json"));

    // this client makes graphql requests with auth
    graphQLClient = GraphQLClient(
        link: Link.from(
            [HttpLink("$baseUrl/graphiql"), AuthLink(getToken: _getToken)]),
        cache: GraphQLCache());
  }

  Future<void> login(LoginData loginData) {
    const String path = "/auth/login";
    return dio
        .post(path, data: loginData.toJson())
        .then((resp) => _onLoginOrRegisterSuccess(resp));
  }

  Future<void> register(RegistrationData registrationData) {
    const String path = "/auth/register";
    return dio
        .post(path, data: registrationData.toJson())
        .then((resp) => _onLoginOrRegisterSuccess(resp));
  }

  void _onLoginOrRegisterSuccess(var resp) {
    _secureStorageService
        .storeAuthData(tokenResponseFromJson(resp.toString()))
        .then((voidVal) {
      _secureStorageService.getUsernameFromToken().then((val) {
        _loggedInUser = val;
        notifyListeners();
      });
    });
  }

  FutureOr<String?> _getToken() {
    _secureStorageService.getAuthData().then((authData) {
      final aT = authData.accessToken;
      final rT = authData.refreshToken;

      if (aT == "" || rT == "") {
        return Future.error("No auth token");
      }
      if (Jwt.isExpired(aT)) {
        return _refreshToken(rT).then((tokens) {
          // if (tokens.accessToken == "") return null;
          return _secureStorageService
              .updateAccessToken(tokens.accessToken)
              .then((val) => 'Bearer ${tokens.accessToken}');
        });
      }
      return 'Bearer $aT';
    });
  }

  Future<TokenResponse> _refreshToken(String refreshToken) async {
    const String path = "/auth/refresh";

    // refresh request
    return dio
        .post(path,
            options: Options(
              headers: {
                'Authorization': 'Bearer $refreshToken',
              },
            ))
        .then((resp) => tokenResponseFromJson(resp.toString()));
  }

  void logout() {}

  void changeUserAuthInfo() {}
}
