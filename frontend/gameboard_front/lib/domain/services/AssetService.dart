import 'package:http/http.dart' as http;

class AssetService {
  static final AssetService _assetService = AssetService._internal();

  factory AssetService() => _assetService;

  AssetService._internal();

  Future fetchAssetsByGameId(int gameId) async {
    final response = await http
        .get("http://localhost:8080/gameboard/asset/${gameId}/assets");

    return response;
  }
}
