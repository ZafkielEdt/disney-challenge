package com.challenge.app.controller;

import com.challenge.app.exception.GenreAlreadyExistsException;
import com.challenge.app.model.request.GenreRequest;
import com.challenge.app.model.response.GenreResponse;
import com.challenge.app.service.abstraction.CreateGenre;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/genres")
@RequiredArgsConstructor
public class GenreController {

  private final CreateGenre createGenre;

  @PostMapping
  public ResponseEntity<GenreResponse> create(@Valid @RequestBody GenreRequest request)
      throws GenreAlreadyExistsException {
    GenreResponse response = createGenre.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
