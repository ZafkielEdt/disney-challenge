package com.challenge.app.service;

import com.challenge.app.exception.ElementAlreadyExistsException;
import com.challenge.app.exception.NotFoundException;
import com.challenge.app.mapper.FilmSeriesMapper;
import com.challenge.app.model.entity.FilmSeries;
import com.challenge.app.model.request.FilmSeriesFilterRequest;
import com.challenge.app.model.request.FilmSeriesRequest;
import com.challenge.app.model.response.FilmSeriesResponse;
import com.challenge.app.model.response.ListFilmSeriesResponse;
import com.challenge.app.repository.FilmSeriesRepository;
import com.challenge.app.service.abstraction.CreateFilmSeries;
import com.challenge.app.service.abstraction.DeleteFilmSeries;
import com.challenge.app.service.abstraction.GetFilmSeries;
import com.challenge.app.service.abstraction.UpdateFilmSeries;
import com.challenge.app.specification.FilmSeriesSpecification;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmSeriesService implements CreateFilmSeries, GetFilmSeries, UpdateFilmSeries,
    DeleteFilmSeries {

  private final FilmSeriesRepository filmSeriesRepository;

  private final FilmSeriesMapper filmSeriesMapper;
  private final FilmSeriesSpecification filmSeriesSpecification;

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
  public ListFilmSeriesResponse getBy(String title, Set<Long> genreId, String order,
      Pageable pageable) throws NotFoundException {

    FilmSeriesFilterRequest filterRequest = new FilmSeriesFilterRequest();
    filterRequest.setTitle(title);
    filterRequest.setGenreId(genreId);
    filterRequest.setOrder(order);

    Page<FilmSeries> page = filmSeriesRepository.findAll(filmSeriesSpecification
        .getByFilters(filterRequest), pageable);

    if (page.isEmpty()) {
      throw new NotFoundException("Any film or series found");
    }

    ListFilmSeriesResponse response = filmSeriesMapper.map(page.getContent());
    buildPageResponse(response, page);

    return response;
  }

  private void buildPageResponse(ListFilmSeriesResponse response, Page<FilmSeries> page) {
    response.setPage(page.getNumber());
    response.setTotalPages(page.getTotalPages());
    response.setSize(page.getSize());
  }

  @Override
  public FilmSeriesResponse update(Long id, FilmSeriesRequest request) throws NotFoundException {

    FilmSeries filmSeries = filmSeriesRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Film or series not found"));

    updateValues(filmSeries, request);

    return filmSeriesMapper.map(filmSeriesRepository.save(filmSeries));
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
