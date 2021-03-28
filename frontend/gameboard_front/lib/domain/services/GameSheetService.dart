import 'package:gameboard_front/helpers/helper.dart';
import 'package:http/http.dart' as http;

class GameSheetService {
  static final GameSheetService _gameSheetService =
      GameSheetService._internal();

  factory GameSheetService() => _gameSheetService;

  GameSheetService._internal();

  Future fetchAllSheet() async {
    final response =
        await http.get(Helper.API_BASE_URL + "/gameboard/gamesheets");

    return response;
  }
}
