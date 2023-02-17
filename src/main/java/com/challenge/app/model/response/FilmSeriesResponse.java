package com.challenge.app.model.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmSeriesResponse {

  private Long id;
  private String title;
  private String releaseDate;
  private Integer rating;
  private String image;
  private List<String> genreNames;
  private List<String> characterNames;
}
