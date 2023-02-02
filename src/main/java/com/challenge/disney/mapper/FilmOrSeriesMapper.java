package com.challenge.disney.mapper;

import com.challenge.disney.model.entity.FilmOrSeries;
import com.challenge.disney.model.request.FilmOrSeriesRequest;
import com.challenge.disney.model.response.FilmOrSeriesResponse;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class FilmOrSeriesMapper {

  public FilmOrSeries map(FilmOrSeriesRequest request) {
    FilmOrSeries filmOrSeries = new FilmOrSeries();
    filmOrSeries.setTitle(request.title());
    filmOrSeries.setReleaseDate(LocalDate.parse(request.releaseDate()));
    filmOrSeries.setRating(request.rating());
    filmOrSeries.setImage(request.image());
    return filmOrSeries;
  }

  public FilmOrSeriesResponse map(FilmOrSeries filmOrSeries) {
    return new FilmOrSeriesResponse(
        filmOrSeries.getTitle(),
        filmOrSeries.getReleaseDate().toString(),
        filmOrSeries.getRating(),
        filmOrSeries.getImage()
    );
  }
}
