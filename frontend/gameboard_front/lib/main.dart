import 'package:flutter/material.dart';
import 'package:gameboard_front/views/auth_page.dart';
import 'package:gameboard_front/views/view_model/comment_model.dart';
import 'package:provider/provider.dart';

import 'views/home_page.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider<CommentModel>(
          create: (context) => CommentModel(),
        ),
      ],
      child: MaterialApp(
        title: 'Game Board',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: AuthPage(),
        routes: {
          "/home-page": (_) => MyHomePage(title: "GameBoard"),
        },
      ),
    );
  }
}
