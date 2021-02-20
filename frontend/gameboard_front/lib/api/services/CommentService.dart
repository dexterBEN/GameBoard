import 'dart:convert';

import 'package:gameboard_front/api/entities/Comment.dart';
import 'package:http/http.dart' as http;

class CommentService {
  static final CommentService _gameSheetService = CommentService._internal();

  factory CommentService() => _gameSheetService;

  CommentService._internal();

  Future fetchCommentsByGameId(int gameId) async {
    final response = await http.get(
        "https://3ae9edcbc520.ngrok.io/gameboard/gamesheet/${gameId}/comments");

    return response;
  }

  void createComment(Comment comment) {
    http.post(
      "https://3ae9edcbc520.ngrok.io/gameboard/comment/create",
      body: {
        "author": comment.author,
        "game_id": comment.gameId.toString(),
        "content": comment.content
      },
    );
  }
}
