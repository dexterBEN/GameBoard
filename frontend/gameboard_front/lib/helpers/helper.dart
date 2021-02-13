import 'dart:convert';
import 'dart:typed_data';
import 'package:base_x/base_x.dart';

class Helper {
  static Uint8List base64ToImg(String imgBase64) {
    return base64Decode(imgBase64);
  }

  // static Uint8List base16ToImg(String imgBase16) {
  //   return hex.decode(imgBase16);
  // }
}
