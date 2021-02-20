import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:gameboard_front/api/entities/GameSheet.dart';
import 'package:gameboard_front/helpers/helper.dart';
import 'package:gameboard_front/views/comments_rates_page.dart';
import 'package:gameboard_front/views/studio_description_page.dart';

class CardComponent extends StatelessWidget {
  final GameSheet gameSheet;

  CardComponent({Key key, this.gameSheet}) : super(key: key);

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
              title: Text(gameSheet.title),
              subtitle: Text('Description comming soon'),
            ),
            Container(
              width: 200,
              height: 200,
              alignment: Alignment.center,
              child: Image.memory(Helper.base64ToImg(gameSheet.jacketPath)),
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
                            studio: gameSheet.studio,
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
                            gameSheet: gameSheet,
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
