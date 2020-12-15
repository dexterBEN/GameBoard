import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:gameboard_front/api/entities/GameSheet.dart';
import 'package:gameboard_front/api/services/GameSheetService.dart';
import 'dart:io' as Io;

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  List<GameSheet> sheets = [];
  GameSheetService gameSheetService = GameSheetService();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    getSheets();
  }

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  getSheets() {
    Iterable list = null;
    gameSheetService.fetchAllSheet().then((response) {
      list = json.decode(response.body);
      setState(() {
        sheets = list.map((model) => GameSheet.fromJson(model)).toList();
      });
      print(sheets);
    });
  }

  List<Widget> displaySheet() {
    var decodeBase64 = null;
    var file = null;
    List<Widget> cardList = [];

    sheets.map((e) {
      decodeBase64 = base64Decode(e.jacketPath);
      cardList.add(
        Card(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              ListTile(
                leading: Icon(Icons.album),
                title: Text(
                  e.title,
                  style: TextStyle(color: Colors.black, fontSize: 10),
                ),
                subtitle: Text('Music by Julie Gable. Lyrics by Sidney Stein.'),
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.end,
                children: <Widget>[
                  TextButton(
                    child: Text('BUY TICKETS'),
                    onPressed: () {/* ... */},
                  ),
                  SizedBox(width: 8),
                  TextButton(
                    child: Text('LISTEN'),
                    onPressed: () {/* ... */},
                  ),
                  SizedBox(width: 8),
                ],
              ),
            ],
          ),
        ),
      );
    });
    return cardList;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Container(
          child: ListView.separated(
            separatorBuilder: (context, index) => SizedBox(height: 18),
            itemCount: sheets.length,
            itemBuilder: (context, index) {
              var decodeBase64 = base64Decode(sheets[index].jacketPath);
              return Card(
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  children: <Widget>[
                    ListTile(
                      leading: Icon(Icons.album),
                      title: Text(sheets[index].title),
                      subtitle: Text('Description comming soon'),
                    ),
                    Container(
                      width: 200,
                      height: 200,
                      alignment: Alignment.center,
                      child: Image.memory(decodeBase64),
                    ),
                    ButtonTheme(
                      // make buttons use the appropriate styles for cards
                      child: ButtonBar(
                        children: <Widget>[
                          FlatButton(
                            child: Text('Flutter'),
                            onPressed: () {/* ... */},
                          ),
                          FlatButton(
                            child: Text('Show More'),
                            onPressed: () {/* ... */},
                          ),
                        ],
                      ),
                    ),
                  ],
                ),
              );
            },
          ),
        ),
      ),
      /*floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ),*/ // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
