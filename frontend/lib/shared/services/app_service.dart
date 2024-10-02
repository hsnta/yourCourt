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
  bool _refreshMode = false;
  late Dio dio;
  late Dio protectedEndpointsDio;
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

  void login(LoginData loginData) {
    const String path = "/auth/login";
    dio
        .post(path, data: loginData.toJson())
        .then((resp) => _onLoginOrRegisterSuccess(resp))
        .onError();
  }

  void register(RegistrationData registrationData) {
    const String path = "/auth/register";
    dio
        .post(path, data: registrationData.toJson())
        .then((resp) => _onLoginOrRegisterSuccess(resp))
        .onError();
  }

  void _onLoginOrRegisterSuccess(var resp) {
    _secureStorageService.storeAuthData(tokenResponseFromJson(resp.toString()));
    _secureStorageService
        .getUsernameFromToken()
        .then((val) => _loggedInUser = val);
    notifyListeners();
  }

  Future<String?> _getToken() async {
    if (_refreshMode)
      return null; // This flag is needed to avoid infinite loop(refresh endpoint calls itself)

    final authData = await _secureStorageService.getAuthData();

    final aT = authData.accessToken;
    final rT = authData.refreshToken;

    if (aT == "" || rT == "") return null;

    if (Jwt.isExpired(aT)) {
      final TokenResponse tokens = await _refreshToken(rT);

      if (tokens == null) return null;

      await _secureStorageService.updateAccessToken(tokens.accessToken);

      return 'Bearer ${tokens.accessToken}';
    }

    return 'Bearer $aT';
  }

  Future<TokenResponse> _refreshToken(String refreshToken) async {
    _refreshMode = true;
    // refresh request

    _refreshMode = false;
  }

  void logout() {}

  void changeUserAuthInfo() {}
}

class RefreshTokenInterceptor {}
