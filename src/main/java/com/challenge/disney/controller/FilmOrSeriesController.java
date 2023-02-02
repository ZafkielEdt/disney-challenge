package com.challenge.disney.controller;

import com.challenge.disney.exception.FilmOrSeriesAlreadyExistsException;
import com.challenge.disney.model.request.FilmOrSeriesRequest;
import com.challenge.disney.model.response.FilmOrSeriesResponse;
import com.challenge.disney.service.abstraction.filmseries.CreateFilmOrSeries;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/filmOrSeries")
@RequiredArgsConstructor
public class FilmOrSeriesController {

  private final CreateFilmOrSeries createFilmOrSeries;


  @PostMapping
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<FilmOrSeriesResponse> create(@Valid @RequestBody FilmOrSeriesRequest request)
      throws FilmOrSeriesAlreadyExistsException {
    FilmOrSeriesResponse response = createFilmOrSeries.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
