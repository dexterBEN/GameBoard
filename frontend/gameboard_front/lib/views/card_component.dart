import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:gameboard_front/domain/entities/GameSheet.dart';
import 'package:gameboard_front/domain/services/AssetService.dart';
import 'package:gameboard_front/helpers/helper.dart';
import 'package:gameboard_front/views/comments_rates_page.dart';
import 'package:gameboard_front/views/media_page.dart';
import 'package:gameboard_front/views/studio_description_page.dart';
import 'package:gameboard_front/views/view_model/asset_model.dart';
import 'package:provider/provider.dart';

class CardComponent extends StatefulWidget {
  CardComponent({Key key, this.gameSheet}) : super(key: key);

  final GameSheet gameSheet;

  @override
  _CardComponentState createState() => _CardComponentState();
}

class _CardComponentState extends State<CardComponent> {
  AssetModel assetModel;
  Future<String> imgB64;

  AssetService assetService = AssetService();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    //fetchGameJacket();
  }

  @override
  void didChangeDependencies() {
    assetModel = Provider.of<AssetModel>(context);
    assetModel.getJacket(widget.gameSheet.jacketPath);
    imgB64 = assetModel.jacketB64;
  }

  // fetchGameJacket() {
  //   assetService.fetchGameJacket(widget.gameSheet.jacketPath).then((response) {
  //     String temp = response.body;
  //     setState(() {
  //       imgB64 = temp;
  //     });
  //   });
  // }

  // Widget buildImg() {
  //   if (imgB64 == null) {
  //     return Text("error img lloading");
  //   }

  //   return Image.memory(Helper.base64ToImg(imgB64));
  // }

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 100,
      child: Card(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            ListTile(
              leading: Icon(Icons.album),
              title: Text(widget.gameSheet.title),
              subtitle: Text('Description comming soon'),
            ),
            Container(
              width: 200,
              height: 200,
              alignment: Alignment.center,
              child: FutureBuilder<String>(
                future: imgB64,
                builder: (context, snapshot) {
                  if (snapshot.connectionState != ConnectionState.done) {
                    return Text("Img loading...");
                  }
                  return Image.memory(Helper.base64ToImg(snapshot.data));
                },
              ),
            ),
            ButtonTheme(
              // make buttons use the appropriate styles for cards
              child: ButtonBar(
                children: <Widget>[
                  FlatButton(
                    child: Text('About the studio'),
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => StudioPage(
                            studio: widget.gameSheet.studio,
                          ),
                        ),
                      );
                    },
                  ),
                  FlatButton(
                    child: Text('Comments and ratings'),
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => CommentPage(
                            title: "GameBoard",
                            gameSheet: widget.gameSheet,
                          ),
                        ),
                      );
                    },
                  ),
                  FlatButton(
                    child: Text('Trailer'),
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => MediaPage(
                            title: "GameBoard",
                            gameSheet: widget.gameSheet,
                          ),
                        ),
                      );
                    },
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
