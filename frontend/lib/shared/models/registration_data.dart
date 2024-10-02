import 'dart:convert';

RegistrationData registrationDataFromJson(String str) =>
    RegistrationData.fromJson(json.decode(str));

String registrationDataToJson(RegistrationData data) =>
    json.encode(data.toJson());

class RegistrationData {
  String username;
  String password;

  RegistrationData({
    required this.username,
    required this.password,
  });

  factory RegistrationData.fromJson(Map<String, dynamic> json) =>
      RegistrationData(
        username: json["username"],
        password: json["password"],
      );

  Map<String, dynamic> toJson() => {
        "username": username,
        "password": password,
      };
}
