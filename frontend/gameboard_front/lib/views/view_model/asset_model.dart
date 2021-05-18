import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:gameboard_front/domain/services/AssetService.dart';

class AssetModel extends ChangeNotifier {
  Future<String> jacketB64;

  AssetService assetService = AssetService();

  Future<String> getJacket(String jacketRef) {
    jacketB64 = assetService.fetchGameJacket(jacketRef);

    return jacketB64;
  }

  // updateJacket(String jacket) {
  //   jacketB64 = jacket;
  //   //jacketHexa = jacket;
  //   notifyListeners();
  // }
}
