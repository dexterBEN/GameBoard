import 'dart:convert';
import 'package:http/http.dart' as http;

class AuthService {
  static final AuthService _authService = AuthService._internal();

  factory AuthService() => _authService;

  AuthService._internal();

  Future login(String email, String password) async {
    final response = await http.post(
      "http://localhost:8080/gameboard/authenticate",
      body: {"email": email, "password": password},
    );
    return response;
  }

  Future register(String name, String email, String password) async {
    final response = await http.post(
      "http://localhost:8080/gameboard/register",
      body: {"name": name, "email": email, "password": password},
    );
  }
}
