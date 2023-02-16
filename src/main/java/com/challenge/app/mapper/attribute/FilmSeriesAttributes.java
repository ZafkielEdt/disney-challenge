package com.challenge.app.mapper.attribute;

public enum FilmSeriesAttributes {

  ID("id"),
  TITLE("title"),
  RELEASE_DATE("releaseDate"),
  RATING("rating"),
  IMAGE("image"),
  CHARACTERS("characters"),
  GENRES("genres");

  private final String fieldName;

  FilmSeriesAttributes(String fieldName) {
    this.fieldName = fieldName;
  }
  
  public String getFieldName() {
    return fieldName;
  }
}
