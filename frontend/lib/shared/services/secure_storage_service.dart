import 'package:flutter_secure_storage/flutter_secure_storage.dart';
import 'package:frontend/shared/models/tokens_response.dart';
import 'package:jwt_decode/jwt_decode.dart';

class SecureStorageService {
  final _storage = const FlutterSecureStorage(
    iOptions: IOSOptions(),
    aOptions: AndroidOptions(
      encryptedSharedPreferences: true,
    ),
  );

  Future<void> storeAuthData(TokenResponse auth) async {
    await _storage.write(key: "accessToken", value: auth.accessToken);
    await _storage.write(key: "refreshToken", value: auth.refreshToken);
  }

  Future<TokenResponse> getAuthData() async {
    final map = await _storage.readAll();
    return TokenResponse(
        accessToken: map["accessToken"] ?? "",
        refreshToken: map["refreshToken"] ?? "");
  }

  Future<String> getUsernameFromToken() async {
    final String? token = await _storage.read(key: "accessToken");
    if (token != null) {
      return Jwt.parseJwt(token)["sub"];
    }
    return "";
  }

  Future<void> updateAccessToken(String token) async {
    await _storage.delete(key: "accessToken");
    await _storage.write(key: "accessToken", value: token);
  }

  Future<void> updateRefreshToken(String token) async {
    await _storage.write(key: "refreshToken", value: token);
  }

  Future<void> clearAuthData() async {
    await _storage.deleteAll();
  }
}
