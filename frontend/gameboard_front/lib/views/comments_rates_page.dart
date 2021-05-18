import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_session/flutter_session.dart';
import 'package:gameboard_front/domain/entities/Comment.dart';
import 'package:gameboard_front/domain/entities/GameSheet.dart';
import 'package:gameboard_front/domain/services/CommentService.dart';
import 'package:gameboard_front/helpers/helper.dart';
import 'package:gameboard_front/views/view_model/asset_model.dart';
import 'package:gameboard_front/views/view_model/comment_model.dart';
import 'dart:html' as html;

import 'package:provider/provider.dart';

class CommentPage extends StatefulWidget {
  CommentPage({Key key, this.gameSheet, this.title}) : super(key: key);

  final GameSheet gameSheet;
  final String title;

  @override
  _CommentPageState createState() => _CommentPageState();
}

class _CommentPageState extends State<CommentPage> {
  TextEditingController _textController = TextEditingController();

  AssetModel assetModel;
  Future<String> imgB64;

  CommentService commentService = CommentService();

  var session = null;

  //List<Comment> comments = [];

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    getUserSession().then((value) => session = value);
  }

  @override
  void didChangeDependencies() {
    assetModel = Provider.of<AssetModel>(context);
    assetModel.getJacket(widget.gameSheet.jacketPath);
    imgB64 = assetModel.jacketB64;
  }

  Future<dynamic> getUserSession() async {
    return await FlutterSession().get("userSession");
  }

  // getComments() {
  //   Iterable list = null;
  //   commentService.fetchCommentsByGameId(widget.gameSheet.id).then((response) {
  //     list = json.decode(response.body);

  //     setState(() {
  //       comments = list.map((model) => Comment.fromJson(model)).toList();
  //     });
  //   });
  // }

  @override
  Widget build(BuildContext context) {
    var commentProvider = Provider.of<CommentModel>(context, listen: false);
    var screenSize = MediaQuery.of(context).size;
    commentProvider.getCommentList(widget.gameSheet.id);

    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: SizedBox(
          width: (screenSize.width * 90) / 100,
          height: (screenSize.height * 90) / 100,
          child: Column(
            children: [
              buildJacketLayout(),
              SizedBox(
                width: screenSize.width / 100,
                height: screenSize.width / 100,
              ),
              buildInputComment(),
              SizedBox(
                width: screenSize.width / 100,
                height: screenSize.width / 100,
              ),
              Expanded(
                child: buildCommentList(),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget buildCommentList() {
    return LayoutBuilder(
      builder: (BuildContext context, BoxConstraints constraints) {
        return SizedBox(
          width: (constraints.maxWidth * 20) / 100,
          //height: constraints.maxHeight,
          child: Consumer<CommentModel>(
            builder: (context, model, _) {
              if (model.comments.length == 0) {
                return Text("no comment for this one");
              }
              return ListView.separated(
                separatorBuilder: (context, index) => SizedBox(height: 5),
                scrollDirection: Axis.vertical,
                shrinkWrap: true,
                itemCount: model.comments.length,
                itemBuilder: (context, index) {
                  return buildCommentBubble(model.comments[index]);
                },
              );
            },
          ),
        );
      },
    );
  }

  Widget buildInputComment() {
    var commentProvider = Provider.of<CommentModel>(context, listen: false);
    return LayoutBuilder(
      builder: (BuildContext context, BoxConstraints constraints) {
        return SizedBox(
          width: (constraints.maxWidth * 20) / 100,
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
              commentProvider.getCommentList(widget.gameSheet.id);
              //html.window.location.reload();
            },
          ),
        );
      },
    );
  }

  Widget buildJacketLayout() {
    return LayoutBuilder(
      builder: (BuildContext context, BoxConstraints constraints) {
        return SizedBox(
          width: (constraints.maxWidth * 20) / 100,
          child: FutureBuilder<String>(
            future: imgB64,
            builder: (context, snapshot) {
              if (snapshot.connectionState != ConnectionState.done) {
                return Text("Img loading...");
              }
              return Image.memory(Helper.base64ToImg(snapshot.data));
            },
          ),
        );
      },
    );
  }

  Widget buildCommentBubble(Comment comment) {
    return LayoutBuilder(
      builder: (BuildContext context, BoxConstraints constraints) {
        return SizedBox(
          width: (constraints.maxWidth * 50) / 100,
          child: Container(
            child: Column(
              children: [
                Text(
                  "@" + Helper.formatUtf8(comment.author) + " say:",
                  style: TextStyle(color: Colors.white),
                ),
                Text(
                  Helper.formatUtf8(comment.content),
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

/*
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
                commentProvider.getCommentList(widget.gameSheet.id);
                //html.window.location.reload();
              },
            ),
          ),
          Center(
            child: Consumer<CommentModel>(
              builder: (context, model, _) {
                if (model.comments.length == 0) {
                  return Text("no comment for this one");
                }
                return SizedBox(
                  width: (screenSize.width * 30) / 100,
                  height: (screenSize.height * 30) / 100,
                  child: ListView.separated(
                    separatorBuilder: (context, index) => SizedBox(height: 18),
                    itemCount: model.comments.length,
                    itemBuilder: (context, index) {
                      return buildCommentBubble(model.comments[index]);
                    },
                  ),
                );
              },
            ),
          ),
 */
