package com.challenge.app.service.abstraction;

import com.challenge.app.exception.NotFoundException;
import com.challenge.app.model.response.FilmSeriesResponse;
import com.challenge.app.model.response.ListFilmSeriesResponse;

public interface GetFilmSeries {

  FilmSeriesResponse get(Long id) throws NotFoundException;

  ListFilmSeriesResponse getAll() throws NotFoundException;
}
