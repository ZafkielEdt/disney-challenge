package com.challenge.app.model.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DCharacterResponse {

  private Long id;
  private String name;
  private Long age;
  private Long weight;
  private String story;
  private String image;
  private List<String> filmsSeriesTitles;
}
