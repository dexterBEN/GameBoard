import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_session/flutter_session.dart';
import 'package:gameboard_front/domain/entities/InputField.dart';
import 'package:gameboard_front/domain/entities/User.dart';
import 'package:gameboard_front/domain/services/AuthService.dart';
import 'package:gameboard_front/views/auth_view/login_form.dart';
import 'package:gameboard_front/views/auth_view/register_form.dart';

class AuthPage extends StatefulWidget {
  AuthPage({Key ? key}) : super(key: key);

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

  List<InputField> loginFormFields = [
    InputField(controller: TextEditingController(), label: "login"),
    InputField(controller: TextEditingController(), label: "password")
  ];

  List<InputField> registerFormFields = [
    InputField(controller: TextEditingController(), label: "name"),
    InputField(controller: TextEditingController(), label: "email"),
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
                          inputFields: loginFormFields,
                        ),
                        RegisterForm(
                          inputFields: registerFormFields,
                          pageController: pageController,
                        ),
                      ],
                    ),
                  ),
                  SwitchButton(pageController: pageController)
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
}

class SwitchButton extends StatefulWidget {
  final PageController pageController;

  SwitchButton({Key? key, required this.pageController}) : super(key: key);

  @override
  _SwitchButtonState createState() => _SwitchButtonState();
}

class _SwitchButtonState extends State<SwitchButton> {
  late double currentPage;

  @override
  void initState() {
    super.initState();
    currentPage = widget.pageController.page!;
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return TextButton(
      onPressed: () {
        widget.pageController.page == 0
            ? widget.pageController.jumpToPage(1)
            : widget.pageController.jumpToPage(0);
        setState(() {
          currentPage = widget.pageController.page!;
        });
      },
      // shape: RoundedRectangleBorder(
      //   borderRadius: BorderRadius.circular(8.0),
      // ),
      // padding: EdgeInsets.all(15.0),
      child: Text(
        currentPage == 0 ? "Go to register form" : "Go to login form",
        style: TextStyle(
          fontSize: 16.0,
          fontWeight: FontWeight.w500,
          color: Colors.grey,
        ),
      ),
    );
  }
}
