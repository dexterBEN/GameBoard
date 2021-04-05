import 'dart:convert';
import 'dart:typed_data';
import 'dart:convert' show utf8;

import 'package:gameboard_front/domain/entities/InputField.dart';

class Helper {
  static String API_BASE_URL = "https://4ccea53c078c.ngrok.io";

  static Uint8List base64ToImg(String imgBase64) {
    return base64Decode(imgBase64);
  }

  static String formatUtf8(String str) {
    var encoded = utf8.encode(str);
    var decoded = utf8.decode(encoded);

    return decoded;
  }

  static List<T> buildJoinedList<T>(List<T> initialList, T separator) {
    List<T> joinedList = [];

    initialList.asMap().forEach((index, value) {
      joinedList.addAll([value, separator]);
    });

    if (joinedList.last == separator) {
      joinedList.removeAt(joinedList.length - 1);
    }
    return joinedList;
  }

  static String getInputValue(List<InputField> fields, String inputName) {
    return fields
        .where((element) => element.label == inputName)
        .toList()[0]
        .controller
        .text;
  }

  // static Uint8List base16ToImg(String imgBase16) {
  //   return hex.decode(imgBase16);
  // }
}
