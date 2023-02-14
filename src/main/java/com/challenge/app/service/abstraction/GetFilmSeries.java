package com.challenge.app.service.abstraction;

import com.challenge.app.exception.NotFoundException;
import com.challenge.app.model.response.FilmSeriesResponse;
import com.challenge.app.model.response.ListFilmSeriesResponse;
import java.util.Set;
import org.springframework.data.domain.Pageable;

public interface GetFilmSeries {

  FilmSeriesResponse get(Long id) throws NotFoundException;

  ListFilmSeriesResponse getAll() throws NotFoundException;

  ListFilmSeriesResponse getBy(String title, Set<Long> genreId, String order, Pageable pageable)
      throws NotFoundException;
}
