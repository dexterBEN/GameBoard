import 'package:flutter/material.dart';
import 'package:gameboard_front/api/entities/Studio.dart';

class StudioPage extends StatefulWidget {
  final Studio studio;

  StudioPage({Key key, this.studio}) : super(key: key);

  @override
  _StudioPageState createState() => _StudioPageState();
}

class _StudioPageState extends State<StudioPage> {
  int indexCarrousel;
  @override
  void initState() {
    super.initState();
    indexCarrousel = 0;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("GameBoard"),
      ),
      body: Center(
        child: Text(widget.studio.name),
      ),
      /*floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: Icon(Icons.add),
      ),*/ // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
