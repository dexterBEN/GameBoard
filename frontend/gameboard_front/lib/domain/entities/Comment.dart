class Comment {
  final int gameId;
  final String author;
  final String content;

  Comment({required this.gameId, required this.author, required this.content});

  factory Comment.fromJson(Map<String, Object> json) {
    return Comment(
      gameId: json["gameID"] as int,
      author: json["author"] as String,
      content: json["content"] as String,
    );
  }
}
