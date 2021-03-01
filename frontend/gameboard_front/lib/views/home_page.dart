import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_session/flutter_session.dart';
import 'package:gameboard_front/domain/entities/GameSheet.dart';
import 'package:gameboard_front/domain/services/GameSheetService.dart';
import 'dart:io' as Io;

import 'package:gameboard_front/views/card_component.dart';

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  List<GameSheet> fetchedSheets = [];
  List<GameSheet> currentList = [];
  GameSheetService gameSheetService = GameSheetService();
  TextEditingController _textController = TextEditingController();
  var session = null;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    getSheets();
    getUserSession().then((value) => print(value));
  }

  Future<dynamic> getUserSession() async {
    session = await FlutterSession().get("userSession");
    return session;
  }

  getSheets() {
    Iterable list = null;
    gameSheetService.fetchAllSheet().then((response) {
      list = json.decode(response.body);
      setState(() {
        fetchedSheets = list.map((model) => GameSheet.fromJson(model)).toList();
        currentList = List.from(fetchedSheets);
      });
      print(fetchedSheets);
    });
  }

  List<GameSheet> filterSearch(String searchParam) {
    setState(() {
      currentList = fetchedSheets
          .where((element) =>
              element.title.toLowerCase().contains(searchParam.toLowerCase()))
          .toList();
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
            child: TextField(
              controller: _textController,
              decoration: InputDecoration(
                hintText: 'Search Here...',
              ),
              onChanged: filterSearch,
            ),
          ),
          Expanded(
            child: Center(
              child: Container(
                child: ListView.separated(
                  separatorBuilder: (context, index) => SizedBox(height: 18),
                  itemCount: currentList.length,
                  itemBuilder: (context, index) {
                    //var decodeBase64 = base64Decode(sheets[index].jacketPath);
                    return CardComponent(gameSheet: currentList[index]);
                  },
                ),
              ),
            ),
          ),
        ],
      ),
      /*floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ),*/ // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
