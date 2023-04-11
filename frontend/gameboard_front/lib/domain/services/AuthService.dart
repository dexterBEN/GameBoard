import 'dart:convert';
import 'package:gameboard_front/helpers/helper.dart';
import 'package:http/http.dart' as http;

class AuthService {
  static final AuthService _authService = AuthService._internal();

  factory AuthService() => _authService;

  AuthService._internal();

  Future login(String email, String password) async {
    final response = await http.post(
      Uri.parse(Helper.API_BASE_URL + "/gameboard/authenticate"),
      body: {"email": email, "password": password},
    );
    return response;
  }

  Future register(String name, String email, String password) async {
    final response = await http.post(
      Uri.parse(Helper.API_BASE_URL + "/gameboard/register"),
      body: {"name": name, "email": email, "password": password},
    );
  }
}
