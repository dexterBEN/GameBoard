class Studio {
  final int id;
  final String name;
  final String director;
  final String headQuarter;
  final String description;
  final String logoRef;

  Studio({
    required this.id,
    required this.name,
    required this.director,
    required this.headQuarter,
    required this.description,
    required this.logoRef,
  });

  factory Studio.fromJson(Map<String, dynamic> json) {
    return Studio(
      id: json['id'],
      name: json['name'],
      director: json['director'],
      headQuarter: json['headQuarter'],
      description: json['description'],
      logoRef: json['logoPathRef'],
    );
  }
}
