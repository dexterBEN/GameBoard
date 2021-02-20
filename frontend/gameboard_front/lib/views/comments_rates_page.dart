import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_session/flutter_session.dart';
import 'package:gameboard_front/api/entities/Comment.dart';
import 'package:gameboard_front/api/entities/GameSheet.dart';
import 'package:gameboard_front/api/services/CommentService.dart';
import 'package:gameboard_front/helpers/helper.dart';
import 'dart:html' as html;

class CommentPage extends StatefulWidget {
  CommentPage({Key key, this.gameSheet, this.title}) : super(key: key);

  final GameSheet gameSheet;
  final String title;

  @override
  _CommentPageState createState() => _CommentPageState();
}

class _CommentPageState extends State<CommentPage> {
  TextEditingController _textController = TextEditingController();

  CommentService commentService = CommentService();

  var session = null;

  List<Comment> comments = [];

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    getUserSession().then((value) => session = value);
    getComments();
  }

  Future<dynamic> getUserSession() async {
    return await FlutterSession().get("userSession");
  }

  getComments() {
    Iterable list = null;
    commentService.fetchCommentsByGameId(widget.gameSheet.id).then((response) {
      list = json.decode(response.body);

      setState(() {
        comments = list.map((model) => Comment.fromJson(model)).toList();
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(12.0),
            child: Text(widget.gameSheet.title),
          ),
          Container(
            width: 250,
            height: 250,
            child: Image.memory(
              Helper.base64ToImg(widget.gameSheet.jacketPath),
            ),
          ),
          Container(
            width: 150,
            height: 80,
            child: TextField(
              controller: _textController,
              decoration: InputDecoration(
                hintText: 'Write your comment',
              ),
              onSubmitted: (inputValue) {
                print(session["user"]["name"]);
                commentService.createComment(
                  Comment(
                    author: session["user"]["name"],
                    content: inputValue,
                    gameId: widget.gameSheet.id,
                  ),
                );
                html.window.location.reload();
              },
            ),
          ),
          Expanded(
            child: Center(
              child: Container(
                width: 800,
                height: 800,
                child: ListView.separated(
                  separatorBuilder: (context, index) => SizedBox(height: 18),
                  itemCount: comments.length,
                  itemBuilder: (context, index) {
                    return buildCommentBubble(comments[index]);
                  },
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }

  Widget buildCommentBubble(Comment comment) {
    return LayoutBuilder(
      builder: (BuildContext context, BoxConstraints constraints) {
        if (comments.length == 0) {
          return SizedBox(
            width: (constraints.maxWidth * 90) / 100,
            height: (constraints.maxHeight * 90) / 100,
            child: Text(
              "no comment for this game yet",
              textAlign: TextAlign.center,
            ),
          );
        }
        return SizedBox(
          width: (constraints.maxWidth * 90) / 100,
          child: Container(
            child: Column(
              children: [
                Text(
                  "@" + comment.author + " say:",
                  style: TextStyle(color: Colors.white),
                ),
                Text(
                  comment.content,
                  style: TextStyle(color: Colors.white),
                )
              ],
            ),
            decoration: BoxDecoration(
              shape: BoxShape.rectangle,
              color: Colors.blue,
              borderRadius: BorderRadius.circular(5),
            ),
          ),
        );
      },
    );
  }
}
