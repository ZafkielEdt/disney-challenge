package com.challenge.app.model.request;

import com.challenge.app.model.entity.Genre;
import java.util.Set;

public class FilmSeriesFilterRequest {

  String title;
  Set<Genre> genreId;
  String order;

  public boolean isASC() {
    return this.order.compareToIgnoreCase("ASC") == 0;
  }

  public boolean isDESC() {
    return this.order.compareToIgnoreCase("DESC") == 0;
  }
}
