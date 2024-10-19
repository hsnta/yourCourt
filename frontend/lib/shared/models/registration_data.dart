import 'dart:convert';

RegistrationData registrationDataFromJson(String str) =>
    RegistrationData.fromJson(json.decode(str));

String registrationDataToJson(RegistrationData data) =>
    json.encode(data.toJson());

class RegistrationData {
  String username;
  String password;
  String email;
  String firstName;
  String lastName;

  RegistrationData({
    required this.username,
    required this.password,
    required this.email,
    required this.firstName,
    required this.lastName,
  });

  factory RegistrationData.fromJson(Map<String, dynamic> json) =>
      RegistrationData(
        username: json["username"],
        password: json["password"],
        email: json["email"],
        firstName: json["firstName"],
        lastName: json["lastName"],
      );

  Map<String, dynamic> toJson() => {
        "username": username,
        "password": password,
        "email": email,
        "firstName": firstName,
        "lastName": lastName,
      };
}
