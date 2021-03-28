import 'package:gameboard_front/helpers/helper.dart';
import 'package:http/http.dart' as http;

class AssetService {
  static final AssetService _assetService = AssetService._internal();

  factory AssetService() => _assetService;

  AssetService._internal();

  Future fetchAssetsByGameId(int gameId) async {
    final response = await http
        .get(Helper.API_BASE_URL + "/gameboard/asset/${gameId}/assets");

    return response;
  }
}
