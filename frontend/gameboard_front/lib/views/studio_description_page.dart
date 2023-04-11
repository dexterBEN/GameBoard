import 'package:flutter/material.dart';
import 'package:gameboard_front/domain/entities/Studio.dart';
import 'package:gameboard_front/helpers/helper.dart';

class StudioPage extends StatefulWidget {
  final Studio studio;

  StudioPage({Key ? key, required this.studio}) : super(key: key);

  @override
  _StudioPageState createState() => _StudioPageState();
}

class _StudioPageState extends State<StudioPage> {
  late int indexCarrousel;
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
        child: Card(
          clipBehavior: Clip.antiAlias,
          child: Column(
            children: [
              ListTile(
                leading: Icon(Icons.arrow_drop_down_circle),
                title: Text(widget.studio.name),
              ),
              Padding(
                padding: EdgeInsets.all(16.0),
                child: Text(
                  widget.studio.description,
                  style: TextStyle(color: Colors.black.withOpacity(0.6)),
                ),
              ),
              Image.memory(Helper.base64ToImg(widget.studio.logoRef)),
            ],
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
