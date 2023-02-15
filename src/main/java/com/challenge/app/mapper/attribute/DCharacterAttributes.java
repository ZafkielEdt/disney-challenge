package com.challenge.app.mapper.attribute;

public enum DCharacterAttributes {

  ID("id"),
  NAME("name"),
  AGE("age"),
  WEIGHT("weight"),
  STORY("story"),
  IMAGE("image"),
  FILMSERIES("filmSeries");

  private final String fieldName;

  DCharacterAttributes(String fieldName) {
    this.fieldName = fieldName;
  }

  public String getFieldName() {
    return this.fieldName;
  }
}
