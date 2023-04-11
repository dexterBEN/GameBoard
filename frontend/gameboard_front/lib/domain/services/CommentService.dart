import 'package:gameboard_front/domain/entities/Comment.dart';
import 'package:gameboard_front/helpers/helper.dart';
import 'package:http/http.dart' as http;

class CommentService {
  static final CommentService _commentService = CommentService._internal();

  factory CommentService() => _commentService;

  CommentService._internal();

  Future fetchCommentsByGameId(int gameId) async {
    final response = await http
        .get(Uri.parse(Helper.API_BASE_URL + "/gameboard/gamesheet/${gameId}/comments"));
    return response;
  }

  void createComment(Comment comment) {
    http.post(
      Uri.parse(Helper.API_BASE_URL + "/gameboard/comment/create"),
      body: {
        "author": comment.author,
        "game_id": comment.gameId.toString(),
        "content": comment.content
      },
    );
  }
}
