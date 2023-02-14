package com.challenge.app.model.request;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmSeriesFilterRequest {

  String title;
  Set<Long> genreId;
  String order;

  public boolean isASC() {
    return this.order.compareToIgnoreCase("ASC") == 0;
  }

  public boolean isDESC() {
    return this.order.compareToIgnoreCase("DESC") == 0;
  }
}
