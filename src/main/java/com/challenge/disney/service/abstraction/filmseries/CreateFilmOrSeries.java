package com.challenge.disney.service.abstraction.filmseries;

import com.challenge.disney.exception.FilmOrSeriesAlreadyExistsException;
import com.challenge.disney.model.request.FilmOrSeriesRequest;
import com.challenge.disney.model.response.FilmOrSeriesResponse;

public interface CreateFilmOrSeries {

  FilmOrSeriesResponse create(FilmOrSeriesRequest request)
      throws FilmOrSeriesAlreadyExistsException;
}
