class Studio {
  final int id;
  final String name;
  final String director;
  final String headQuarter;
  final String description;
  final String logoRef;

  Studio({
    this.id,
    this.name,
    this.director,
    this.headQuarter,
    this.description,
    this.logoRef,
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
