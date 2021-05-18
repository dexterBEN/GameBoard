import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:gameboard_front/domain/entities/Comment.dart';
import 'package:gameboard_front/domain/services/AssetService.dart';
import 'package:gameboard_front/domain/services/CommentService.dart';

class CommentModel extends ChangeNotifier {
  List<Comment> comments = [];
  String jacketB64;

  CommentService commentService = CommentService();

  getCommentList(int gameId) {
    Iterable list = [];
    List<Comment> el = [];
    commentService.fetchCommentsByGameId(gameId).then((response) {
      list = json.decode(response.body);
      updateCommentList(list.map((model) => Comment.fromJson(model)).toList());
    });
  }

  updateCommentList(List<Comment> newComments) {
    comments = newComments;
    notifyListeners();
  }
}
