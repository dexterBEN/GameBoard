import 'package:http/http.dart' as http;

class AuthService {
  static final AuthService _gameSheetService = AuthService._internal();

  factory AuthService() => _gameSheetService;

  AuthService._internal();

  Future login(String email, String password) async {
    final response = await http.post(
      "http://localhost:8080/gameboard/authenticate",
      body: {"email": email, "password": password},
    );

    return response;
  }
}
