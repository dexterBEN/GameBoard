import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_session/flutter_session.dart';
import 'package:gameboard_front/domain/entities/InputField.dart';
import 'package:gameboard_front/domain/entities/User.dart';
import 'package:gameboard_front/domain/services/AuthService.dart';
import 'package:gameboard_front/helpers/helper.dart';

class RegisterForm extends StatelessWidget {
  List<InputField> inputFields;
  final PageController pageController;

  RegisterForm({Key ? key, required this.inputFields, required this.pageController})
      : super(key: key);

  AuthService authService = AuthService();
  FlutterSession session = FlutterSession();

  bool isSubmitted = false;

  @override
  Widget build(BuildContext context) {
    List<Widget> childs = [];
    childs.add(separatorContainer());
    childs = buildInputs(childs);

    childs = Helper.buildJoinedList(childs, separatorContainer());
    return Card(
      color: Colors.white,
      child: Column(
        children: childs,
      ),
    );
  }

  Widget separatorContainer() {
    return LayoutBuilder(
      builder: (BuildContext context, BoxConstraints constraints) {
        return SizedBox(
          width: 10,
          height: 10,
        );
      },
    );
  }

  List<Widget> buildInputs(List<Widget> childs) {
    inputFields.map((input) {
      childs.add(
        LayoutBuilder(
          builder: (BuildContext context, BoxConstraints constraints) {
            return SizedBox(
              width: (constraints.maxWidth * 60) / 100,
              child: TextField(
                obscureText: input.label == "password",
                controller: input.controller,
                decoration: InputDecoration(
                  filled: true,
                  fillColor: Colors.white,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(8),
                  ),
                  hintText: input.label,
                  hintStyle: TextStyle(
                    color: Colors.black,
                  ),
                ),
              ),
            );
          },
        ),
      );
    }).toList();

    childs.add(
      LayoutBuilder(
        builder: (BuildContext context, BoxConstraints constraints) {
          return ElevatedButton(
            onPressed: () {
              var pwdValue = inputFields
                  .where((element) => element.label == "password")
                  .toList()[0]
                  .controller
                  .text;

              var emailValue = inputFields
                  .where((element) => element.label == "email")
                  .toList()[0]
                  .controller
                  .text;

              var nameValue = inputFields
                  .where((element) => element.label == "name")
                  .toList()[0]
                  .controller
                  .text;

              authService
                  .register(nameValue, emailValue, pwdValue)
                  .then((value) {
                //pageController.jumpToPage(0);
                pageController.jumpToPage(0);
              });
            },
            // child: RoundedRectangleBorder(
            //   borderRadius: BorderRadius.circular(8.0),
            // ),
            // padding: EdgeInsets.all(15.0),
            child: Text(
              "Register",
              style: TextStyle(
                fontSize: 16.0,
                fontWeight: FontWeight.w500,
                color: Colors.white,
              ),
            ),
          );
        },
      ),
    );
    return childs;
  }
}
