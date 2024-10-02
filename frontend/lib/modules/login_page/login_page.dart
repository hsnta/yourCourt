import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});
  @override
  State<LoginPage> createState() {
    return LoginPageState();
  }
}

class LoginPageState extends State<LoginPage> {
  bool registrationMode = false;
  final _loginFormKey = GlobalKey<FormState>();
  final TextEditingController _userNameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  final TextEditingController _firstNameController = TextEditingController();
  final TextEditingController _lastNameController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();

  @override
  void dispose() {
    _userNameController.dispose();
    _passwordController.dispose();
    _firstNameController.dispose();
    _lastNameController.dispose();
    _emailController.dispose();
    super.dispose();
  }

  void switchMode() {
    setState(() => registrationMode = !registrationMode);
  }

  String? _userNameValidator(String? value) {
    if (value == null || value.isEmpty) {
      return 'Please enter some text';
    }
    return null;
  }

  String? _passwordValidator(String? value) {
    if (value == null || value.isEmpty) {
      return 'Please enter some text';
    }
    return null;
  }

  void login() {}

  void register() {}

  @override
  Widget build(BuildContext context) {
    return Center(
        child: Form(
      key: _loginFormKey,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          TextFieldWithPadding(
              controller: _userNameController,
              valdator: _userNameValidator,
              hint: "Username or email"),
          TextFieldWithPadding(
              controller: _passwordController,
              valdator: _passwordValidator,
              hint: "Password",
              applyVerticalPadding: true),
          if (registrationMode) ...[
            TextFieldWithPadding(
                controller: _emailController,
                hint: "Email",
                applyVerticalPadding: true),
            TextFieldWithPadding(
                controller: _firstNameController,
                hint: "First name",
                applyVerticalPadding: true),
            TextFieldWithPadding(
                controller: _lastNameController,
                hint: "Last name",
                applyVerticalPadding: true),
          ],
          registrationMode
              ? RichText(
                  text: TextSpan(children: [
                  const TextSpan(
                      text: "Already have an account? ",
                      style: TextStyle(color: Colors.white)),
                  TextSpan(
                      text: "Back to login page",
                      recognizer: TapGestureRecognizer()..onTap = switchMode,
                      style: const TextStyle(color: Colors.blue))
                ]))
              : RichText(
                  text: TextSpan(children: [
                  const TextSpan(
                      text: "No account? ",
                      style: TextStyle(color: Colors.white)),
                  TextSpan(
                      text: "Register",
                      recognizer: TapGestureRecognizer()..onTap = switchMode,
                      style: const TextStyle(color: Colors.blue))
                ])),
          Padding(
            padding: const EdgeInsets.symmetric(vertical: 16),
            child: ElevatedButton(
              onPressed: () {
                if (_loginFormKey.currentState!.validate()) {
                  // If the form is valid, display a snackbar. In the real world,
                  // you'd often call a server or save the information in a database.
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(content: Text('Processing Data')),
                  );
                  if (registrationMode) {
                    register();
                    return;
                  }
                  login();
                }
              },
              child: Text(registrationMode ? 'Register' : 'Login'),
            ),
          ),
        ],
      ),
    ));
  }
}

class TextFieldWithPadding extends StatelessWidget {
  final TextEditingController controller;
  final FormFieldValidator<String>? valdator;
  final String? hint;
  final bool applyVerticalPadding;

  const TextFieldWithPadding(
      {super.key,
      required this.controller,
      this.valdator,
      this.hint,
      this.applyVerticalPadding = (false)});

  @override
  Widget build(BuildContext context) {
    return Padding(
        padding: EdgeInsets.only(
            left: 8, right: 8, top: applyVerticalPadding ? 16 : 0),
        child: TextFormField(
          validator: valdator,
          decoration: InputDecoration(
            border: const OutlineInputBorder(),
            hintText: hint,
          ),
        ));
  }
}
