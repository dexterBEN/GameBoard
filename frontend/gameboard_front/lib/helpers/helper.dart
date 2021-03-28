import 'dart:convert';
import 'dart:typed_data';
import 'dart:convert' show utf8;

class Helper {
  static String API_BASE_URL = "https://61cb564c3a82.ngrok.io";

  static Uint8List base64ToImg(String imgBase64) {
    return base64Decode(imgBase64);
  }

  static String formatUtf8(String str) {
    var encoded = utf8.encode(str);
    var decoded = utf8.decode(encoded);

    return decoded;
  }

  // static Uint8List base16ToImg(String imgBase16) {
  //   return hex.decode(imgBase16);
  // }
}
