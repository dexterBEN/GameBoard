import 'Studio.dart';

class GameSheet {
  final int id;
  final String title;
  final String platform;
  final String ageRestriction;
  final String jacketPath;
  final String creationDate;
  final String updatedDate;

  final Studio studio;

  GameSheet(
      {this.id,
      this.title,
      this.platform,
      this.ageRestriction,
      this.jacketPath,
      this.creationDate,
      this.updatedDate,
      this.studio});

  factory GameSheet.fromJson(Map<String, dynamic> json) {
    return GameSheet(
      id: json['id'],
      title: json['title'],
      platform: json['platform'],
      ageRestriction: json['ageRestriction'],
      jacketPath: json['jacketPathRef'],
      creationDate: json['creationDate'],
      updatedDate: json['updatedDate'],
      studio: Studio.fromJson(json['studio']),
    );
  }
}
