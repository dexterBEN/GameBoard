class Comment {
  final int gameId;
  final String author;
  final String content;

  Comment({this.gameId, this.author, this.content});

  factory Comment.fromJson(Map<String, Object> json) {
    return Comment(
      gameId: json["gameID"],
      author: json["author"],
      content: json["content"],
    );
  }
}
