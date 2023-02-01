package com.challenge.disney.service;

import com.challenge.disney.exception.FilmOrSeriesAlreadyExistsException;
import com.challenge.disney.mapper.FilmOrSeriesMapper;
import com.challenge.disney.model.entity.FilmOrSeries;
import com.challenge.disney.model.request.FilmOrSeriesRequest;
import com.challenge.disney.model.response.FilmOrSeriesResponse;
import com.challenge.disney.repository.FilmOrSeriesRepository;
import com.challenge.disney.service.abstraction.filmseries.CreateFilmOrSeries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateFilmOrSeriesImpl implements CreateFilmOrSeries {

  private final FilmOrSeriesRepository filmOrSeriesRepository;

  private final FilmOrSeriesMapper filmOrSeriesMapper;

  @Override
  public FilmOrSeriesResponse create(FilmOrSeriesRequest request)
      throws FilmOrSeriesAlreadyExistsException {

    if (filmOrSeriesRepository.existsByTitle(request.title())) {
      throw new FilmOrSeriesAlreadyExistsException("Film or Series already exists");
    }

    FilmOrSeries filmOrSeries = filmOrSeriesMapper.map(request);

    return filmOrSeriesMapper.map(filmOrSeriesRepository.save(filmOrSeries));
  }
}
