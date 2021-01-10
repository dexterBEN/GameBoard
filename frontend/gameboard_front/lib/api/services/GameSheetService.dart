import 'dart:convert';

import 'package:http/http.dart' as http;

class GameSheetService {
  static final GameSheetService _gameSheetService =
      GameSheetService._internal();

  factory GameSheetService() => _gameSheetService;

  GameSheetService._internal();

  Future fetchAllSheet() async {
    final response =
        await http.get("https://258d56cf8bf8.ngrok.io/gamesheet/sheets");

    return response;
  }
}
