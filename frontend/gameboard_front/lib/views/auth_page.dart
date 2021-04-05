import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_session/flutter_session.dart';
import 'package:gameboard_front/domain/entities/InputField.dart';
import 'package:gameboard_front/domain/entities/User.dart';
import 'package:gameboard_front/domain/services/AuthService.dart';
import 'package:gameboard_front/views/auth_view/login_form.dart';

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

  final emailCtrl2 = TextEditingController();
  final passwordCtrl2 = TextEditingController();

  final nameCtrl = TextEditingController();

  final pageController = PageController(
    initialPage: 0,
  );

  List<InputField> inputFields = [
    InputField(controller: TextEditingController(), label: "login"),
    InputField(controller: TextEditingController(), label: "password")
  ];

  FlutterSession session = FlutterSession();

  AuthService authService = AuthService();

  var currentPage = 0.0;

  @override
  Widget build(BuildContext context) {
    var screenSize = MediaQuery.of(context).size;
    return Scaffold(
      body: LayoutBuilder(
        builder: (BuildContext context, BoxConstraints constraints) {
          return Center(
            child: SizedBox(
              width: (screenSize.width * 40) / 100,
              height: (screenSize.height * 40) / 100,
              child: Column(
                children: [
                  Expanded(
                    child: PageView(
                      controller: pageController,
                      children: [
                        LoginForm(
                          inputFields: inputFields,
                        ),
                        buildRegisterForm(),
                      ],
                    ),
                  ),
                  RaisedButton(
                    onPressed: () {
                      pageController.page == 0
                          ? pageController.jumpToPage(1)
                          : pageController.jumpToPage(0);
                      setState(() {
                        currentPage = pageController.page;
                      });
                    },
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(8.0),
                    ),
                    padding: EdgeInsets.all(15.0),
                    child: Text(
                      currentPage == 0
                          ? "Go to register form"
                          : "Go to login form",
                      style: TextStyle(
                        fontSize: 16.0,
                        fontWeight: FontWeight.w500,
                        color: Colors.grey,
                      ),
                    ),
                  )
                ],
              ),
            ),
          );
        },
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
                        hintText: "email",
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
                      obscureText: true,
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
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(8.0),
                ),
                padding: EdgeInsets.all(15.0),
                child: Text(
                  "Create your account",
                  style: TextStyle(
                    fontSize: 16.0,
                    fontWeight: FontWeight.w500,
                    color: Colors.white,
                  ),
                ),
                onPressed: () {
                  authService
                      .register(
                          nameCtrl.text, emailCtrl2.text, passwordCtrl2.text)
                      .then((value) {
                    pageController.jumpToPage(0);
                  });
                },
              ),
              Container(
                width: 20,
                height: 20,
              ),
              // RaisedButton(
              //   onPressed: () {
              //     pageController.jumpToPage(0);
              //   },
              //   shape: RoundedRectangleBorder(
              //     borderRadius: BorderRadius.circular(8.0),
              //   ),
              //   padding: EdgeInsets.all(15.0),
              //   child: Text(
              //     "Go To Login Form",
              //     style: TextStyle(
              //       fontSize: 16.0,
              //       fontWeight: FontWeight.w500,
              //       color: Colors.white,
              //     ),
              //   ),
              // ),
            ],
          ),
        ),
      ),
    );
  }
}
