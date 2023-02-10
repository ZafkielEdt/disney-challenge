package com.challenge.app.service.abstraction;

import com.challenge.app.model.response.FilmSeriesResponse;
import java.util.List;

public interface GetFilmSeries {

  FilmSeriesResponse get(Long id);

  List<FilmSeriesResponse> getAll();
}
