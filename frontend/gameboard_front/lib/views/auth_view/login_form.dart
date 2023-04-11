import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_session/flutter_session.dart';
import 'package:gameboard_front/domain/entities/InputField.dart';
import 'package:gameboard_front/domain/entities/User.dart';
import 'package:gameboard_front/domain/services/AuthService.dart';
import 'package:gameboard_front/helpers/helper.dart';

class LoginForm extends StatelessWidget {
  List<InputField> inputFields;

  LoginForm({Key? key, required this.inputFields}) : super(key: key);

  FlutterSession session = FlutterSession();
  AuthService authService = AuthService();

  void setSessionData(sessionData) async {
    await session.set("userSession", sessionData);
  }

  @override
  Widget build(BuildContext context) {
    List<Widget> childs = [];
    childs.add(separatorContainer());
    childs = buildInputs(childs);

    childs = Helper.buildJoinedList(childs, separatorContainer());
    return Card(
      color: Colors.blue[500],
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
              UserData? userData = null;
              var pwdValue = inputFields
                  .where((element) => element.label == "password")
                  .toList()[0]
                  .controller
                  .text;

              var loginValue = inputFields
                  .where((element) => element.label == "login")
                  .toList()[0]
                  .controller
                  .text;

              authService.login(loginValue, pwdValue).then((response) {
                userData = UserData.fromJson(json.decode(response.body));

                print(userData!.user.email);

                setSessionData(userData);
              });
              Navigator.of(context).pushNamed('/home-page');
            },
            // shape: RoundedRectangleBorder(
            //   borderRadius: BorderRadius.circular(8.0),
            // ),
            // padding: EdgeInsets.all(15.0),
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
    );
    return childs;
  }
}
