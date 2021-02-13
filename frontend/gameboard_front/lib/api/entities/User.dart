class User {
  final String email;
  final String name;
  final String password;

  User({this.email, this.password, this.name});

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      name: json['name'],
      email: json['email'],
      password: json['passWord'],
    );
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = Map<String, dynamic>();

    data["email"] = email;
    data["name"] = name;
    data["passWord"] = password;
    return data;
  }
}

class UserData {
  final User user;
  final String token;

  UserData({this.user, this.token});

  factory UserData.fromJson(Map<String, dynamic> json) {
    return UserData(
      token: json['token'],
      user: User.fromJson(json['user']),
    );
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = Map<String, dynamic>();

    data["user"] = this.user;
    data["token"] = this.token;
    return data;
  }
}

class Session {
  final UserData userData;

  Session({this.userData});

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = Map<String, dynamic>();

    data["user"] = this.userData.user;
    data["token"] = this.userData.token;
    return data;
  }
}
