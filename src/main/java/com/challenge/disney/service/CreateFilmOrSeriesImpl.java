package com.challenge.disney.service;

import com.challenge.disney.model.request.FilmOrSeriesRequest;
import com.challenge.disney.model.response.FilmOrSeriesResponse;
import com.challenge.disney.service.abstraction.filmseries.CreateFilmOrSeries;
import org.springframework.stereotype.Service;

@Service
public class CreateFilmOrSeriesImpl implements CreateFilmOrSeries {

  @Override
  public FilmOrSeriesResponse create(FilmOrSeriesRequest request) {
    return null;
  }
}
