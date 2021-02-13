import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_session/flutter_session.dart';
import 'package:gameboard_front/api/entities/User.dart';
import 'package:gameboard_front/api/services/AuthService.dart';

class AuthPage extends StatefulWidget {
  AuthPage({Key key}) : super(key: key);

  @override
  _AuthPageState createState() => _AuthPageState();
}

class _AuthPageState extends State<AuthPage> {
  @override
  void initState() {
    super.initState();
  }

  final emailCtrl = TextEditingController();
  final passwordCtrl = TextEditingController();

  final emailCtrl2 = TextEditingController();
  final passwordCtrl2 = TextEditingController();

  final nameCtrl = TextEditingController();

  final pageController = PageController(
    initialPage: 0,
  );

  FlutterSession session = FlutterSession();

  AuthService authService = AuthService();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: PageView(
        controller: pageController,
        children: [
          buildLoginForm(),
          buildRegisterForm(),
        ],
      ),
    );
  }

  void setSessionData(sessionData) async {
    await session.set("userSession", sessionData);
  }

  getSession() async {
    var sessionData = null;
    sessionData = await session.get("userSession");
    return sessionData;
  }

  Widget buildRegisterForm() {
    return Center(
      child: SizedBox(
        width: 800,
        height: 400,
        child: Card(
          color: Colors.white30,
          child: Column(
            children: [
              LayoutBuilder(
                builder: (BuildContext context, BoxConstraints constraints) {
                  return SizedBox(
                    width: (constraints.maxWidth * 90) / 100,
                    child: TextField(
                      controller: nameCtrl,
                      decoration: InputDecoration(
                        filled: true,
                        fillColor: Colors.white,
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(8),
                        ),
                        hintText: "name",
                        hintStyle: TextStyle(
                          color: Colors.black,
                        ),
                      ),
                    ),
                  );
                },
              ),
              LayoutBuilder(
                builder: (BuildContext context, BoxConstraints constraints) {
                  return SizedBox(
                    width: (constraints.maxWidth * 90) / 100,
                    child: TextField(
                      controller: emailCtrl2,
                      decoration: InputDecoration(
                        filled: true,
                        fillColor: Colors.white,
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(8),
                        ),
                        hintText: "login",
                        hintStyle: TextStyle(
                          color: Colors.black,
                        ),
                      ),
                    ),
                  );
                },
              ),
              LayoutBuilder(
                builder: (BuildContext context, BoxConstraints constraints) {
                  return SizedBox(
                    width: (constraints.maxWidth * 90) / 100,
                    child: TextField(
                      controller: passwordCtrl2,
                      decoration: InputDecoration(
                        filled: true,
                        fillColor: Colors.white,
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(8),
                        ),
                        hintText: "password",
                        hintStyle: TextStyle(
                          color: Colors.black,
                        ),
                      ),
                    ),
                  );
                },
              ),
              Container(
                width: 20,
                height: 20,
              ),
              RaisedButton(
                onPressed: () {},
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(8.0),
                ),
                padding: EdgeInsets.all(15.0),
                child: Text(
                  "Login",
                  style: TextStyle(
                    fontSize: 16.0,
                    fontWeight: FontWeight.w500,
                    color: Colors.white,
                  ),
                ),
              ),
              Container(
                width: 20,
                height: 20,
              ),
              RaisedButton(
                onPressed: () {
                  pageController.jumpToPage(0);
                },
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(8.0),
                ),
                padding: EdgeInsets.all(15.0),
                child: Text(
                  "Go To Login Form",
                  style: TextStyle(
                    fontSize: 16.0,
                    fontWeight: FontWeight.w500,
                    color: Colors.white,
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget buildLoginForm() {
    return Center(
      child: SizedBox(
        width: 800,
        height: 400,
        child: Card(
          color: Colors.blue[500],
          child: Column(
            children: [
              LayoutBuilder(
                builder: (BuildContext context, BoxConstraints constraints) {
                  return SizedBox(
                    width: (constraints.maxWidth * 90) / 100,
                    child: TextField(
                      controller: emailCtrl,
                      decoration: InputDecoration(
                        filled: true,
                        fillColor: Colors.white,
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(8),
                        ),
                        hintText: "login",
                        hintStyle: TextStyle(
                          color: Colors.black,
                        ),
                      ),
                    ),
                  );
                },
              ),
              LayoutBuilder(
                builder: (BuildContext context, BoxConstraints constraints) {
                  return SizedBox(
                    width: (constraints.maxWidth * 90) / 100,
                    child: TextField(
                      controller: passwordCtrl,
                      decoration: InputDecoration(
                        filled: true,
                        fillColor: Colors.white,
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(8),
                        ),
                        hintText: "password",
                        hintStyle: TextStyle(
                          color: Colors.black,
                        ),
                      ),
                    ),
                  );
                },
              ),
              Container(
                width: 20,
                height: 20,
              ),
              LayoutBuilder(
                builder: (BuildContext context, BoxConstraints constraints) {
                  return RaisedButton(
                    onPressed: () {
                      UserData userData = null;
                      authService
                          .login(emailCtrl.text, passwordCtrl.text)
                          .then((response) {
                        userData =
                            UserData.fromJson(json.decode(response.body));

                        print(userData.user.email);

                        setSessionData(userData);
                      });
                      Navigator.of(context).pushNamed('/home-page');
                    },
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(8.0),
                    ),
                    padding: EdgeInsets.all(15.0),
                    child: Text(
                      "Login",
                      style: TextStyle(
                        fontSize: 16.0,
                        fontWeight: FontWeight.w500,
                        color: Colors.white,
                      ),
                    ),
                  );
                },
              ),
              Container(
                width: 20,
                height: 20,
              ),
              RaisedButton(
                onPressed: () {
                  pageController.jumpToPage(1);
                },
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(8.0),
                ),
                padding: EdgeInsets.all(15.0),
                child: Text(
                  "Create account",
                  style: TextStyle(
                    fontSize: 16.0,
                    fontWeight: FontWeight.w500,
                    color: Colors.white,
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
