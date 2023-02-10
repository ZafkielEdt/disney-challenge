package com.challenge.app.service.abstraction;

import com.challenge.app.exception.ElementAlreadyExistsException;
import com.challenge.app.exception.NotFoundException;
import com.challenge.app.model.request.FilmSeriesRequest;
import com.challenge.app.model.response.FilmSeriesResponse;

public interface CreateFilmSeries {

  FilmSeriesResponse create(FilmSeriesRequest request)
      throws ElementAlreadyExistsException, NotFoundException;
}
