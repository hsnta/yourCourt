import 'dart:convert';

LoginData loginDataFromJson(String str) => LoginData.fromJson(json.decode(str));

String loginDataToJson(LoginData data) => json.encode(data.toJson());

class LoginData {
  String username;
  String password;

  LoginData({
    required this.username,
    required this.password,
  });

  factory LoginData.fromJson(Map<String, dynamic> json) => LoginData(
        username: json["username"],
        password: json["password"],
      );

  Map<String, dynamic> toJson() => {
        "username": username,
        "password": password,
      };
}
