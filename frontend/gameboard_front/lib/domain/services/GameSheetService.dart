import 'package:http/http.dart' as http;

class GameSheetService {
  static final GameSheetService _gameSheetService =
      GameSheetService._internal();

  factory GameSheetService() => _gameSheetService;

  GameSheetService._internal();

  Future fetchAllSheet() async {
    final response =
        await http.get("http://localhost:8080/gameboard/gamesheets");

    return response;
  }
}
