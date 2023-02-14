package com.challenge.app.controller;

import com.challenge.app.common.PaginatedResultsRetrieved;
import com.challenge.app.exception.ElementAlreadyExistsException;
import com.challenge.app.exception.NotFoundException;
import com.challenge.app.model.request.FilmSeriesRequest;
import com.challenge.app.model.response.FilmSeriesResponse;
import com.challenge.app.model.response.ListFilmSeriesResponse;
import com.challenge.app.service.abstraction.CreateFilmSeries;
import com.challenge.app.service.abstraction.DeleteFilmSeries;
import com.challenge.app.service.abstraction.GetFilmSeries;
import com.challenge.app.service.abstraction.UpdateFilmSeries;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v2/filmseries")
@RequiredArgsConstructor
public class FilmSeriesController {

  private final CreateFilmSeries createFilmSeries;
  private final GetFilmSeries getFilmSeries;
  private final UpdateFilmSeries updateFilmSeries;
  private final DeleteFilmSeries deleteFilmSeries;
  private final PaginatedResultsRetrieved resultsRetrieved;

  @PostMapping
  public ResponseEntity<FilmSeriesResponse> create(@Valid @RequestBody FilmSeriesRequest request)
      throws ElementAlreadyExistsException, NotFoundException {
    FilmSeriesResponse response = createFilmSeries.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FilmSeriesResponse> get(@PathVariable(value = "id") Long id)
      throws NotFoundException {
    FilmSeriesResponse response = getFilmSeries.get(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<ListFilmSeriesResponse> getAll() throws NotFoundException {
    ListFilmSeriesResponse listFilmSeriesResponse = getFilmSeries.getAll();
    return ResponseEntity.ok(listFilmSeriesResponse);
  }

  @GetMapping("/filter")
  public ResponseEntity<ListFilmSeriesResponse> getAllBy(
      @RequestParam(required = false) String title,
      @RequestParam(required = false) Set<Long> genreId,
      @RequestParam(required = false, defaultValue = "ASC") String order,
      Pageable pageable, UriComponentsBuilder uriComponentsBuilder,
      HttpServletResponse servletResponse
  ) throws NotFoundException {
    ListFilmSeriesResponse response = getFilmSeries.getBy(title, genreId, order, pageable);

    resultsRetrieved.addLinkHeaderOnPagedResourceRetrieval(
        uriComponentsBuilder,
        servletResponse,
        "/characters/filter",
        response.getPage(),
        response.getTotalPages(),
        response.getSize()
    );

    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<FilmSeriesResponse> update(@PathVariable(value = "id") Long id,
      @Valid @RequestBody FilmSeriesRequest request) throws NotFoundException {
    FilmSeriesResponse response = updateFilmSeries.update(id, request);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) throws NotFoundException {
    deleteFilmSeries.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
