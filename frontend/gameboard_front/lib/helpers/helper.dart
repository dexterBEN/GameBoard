import 'dart:convert';
import 'dart:typed_data';

class Helper {
  static Uint8List base64ToImg(String imgBase64) {
    return base64Decode(imgBase64);
  }
}
