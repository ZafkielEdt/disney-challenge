package com.challenge.app.service;

import com.challenge.app.exception.ElementAlreadyExistsException;
import com.challenge.app.exception.NotFoundException;
import com.challenge.app.mapper.FilmSeriesMapper;
import com.challenge.app.model.entity.FilmSeries;
import com.challenge.app.model.request.FilmSeriesRequest;
import com.challenge.app.model.response.FilmSeriesResponse;
import com.challenge.app.model.response.ListFilmSeriesResponse;
import com.challenge.app.repository.FilmSeriesRepository;
import com.challenge.app.service.abstraction.CreateFilmSeries;
import com.challenge.app.service.abstraction.DeleteFilmSeries;
import com.challenge.app.service.abstraction.GetFilmSeries;
import com.challenge.app.service.abstraction.UpdateFilmSeries;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmSeriesService implements CreateFilmSeries, GetFilmSeries, UpdateFilmSeries,
    DeleteFilmSeries {

  private final FilmSeriesRepository filmSeriesRepository;

  private final FilmSeriesMapper filmSeriesMapper;

  @Override
  public FilmSeriesResponse create(FilmSeriesRequest request)
      throws ElementAlreadyExistsException, NotFoundException {

    if (filmSeriesRepository.existsByTitle(request.title())) {
      throw new ElementAlreadyExistsException("Film or Series already exists");
    }

    FilmSeries filmSeries = filmSeriesMapper.map(request);


    return filmSeriesMapper.map(filmSeriesRepository.save(filmSeries));
  }

  @Override
  public FilmSeriesResponse get(Long id) throws NotFoundException {

    FilmSeries filmSeries = filmSeriesRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Film or series not found"));

    return filmSeriesMapper.map(filmSeries);
  }

  @Override
  public ListFilmSeriesResponse getAll() throws NotFoundException {

    List<FilmSeries> filmSeries = filmSeriesRepository.findAll();

    if (filmSeries.isEmpty()) {
      throw new NotFoundException("Any Film or series found");
    }

    return filmSeriesMapper.map(filmSeries);
  }

  @Override
  public FilmSeriesResponse update(Long id, FilmSeriesRequest request) throws NotFoundException {

    FilmSeries filmSeries = filmSeriesRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Film or series not found"));

    updateValues(filmSeries, request);

    return filmSeriesMapper.map(filmSeries);
  }

  @Override
  public void delete(Long id) throws NotFoundException {
    FilmSeries filmSeries = filmSeriesRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Film or series not found"));

    filmSeriesRepository.delete(filmSeries);
  }

  private void updateValues(FilmSeries filmSeries, FilmSeriesRequest request) {
    if (!request.title().isBlank()) {
      filmSeries.setTitle(request.title());
    }
    if (!request.releaseDate().isBlank()) {
      filmSeries.setReleaseDate(LocalDate.parse(request.releaseDate()));
    }
    if (request.rating() != null) {
      filmSeries.setRating(request.rating());
    }
    if (!request.image().isBlank()) {
      filmSeries.setImage(request.image());
    }
  }
}
