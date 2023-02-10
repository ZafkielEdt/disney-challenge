package com.challenge.app.controller;

import com.challenge.app.exception.GenreAlreadyExistsException;
import com.challenge.app.exception.NotFoundException;
import com.challenge.app.model.request.GenreRequest;
import com.challenge.app.model.response.GenreResponse;
import com.challenge.app.service.abstraction.CreateGenre;
import com.challenge.app.service.abstraction.DeleteGenre;
import com.challenge.app.service.abstraction.GetGenre;
import com.challenge.app.service.abstraction.UpdateGenre;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/genres")
@RequiredArgsConstructor
public class GenreController {

  private final CreateGenre createGenre;
  private final GetGenre getGenre;
  private final UpdateGenre updateGenre;
  private final DeleteGenre deleteGenre;

  @PostMapping
  public ResponseEntity<GenreResponse> create(@Valid @RequestBody GenreRequest request)
      throws GenreAlreadyExistsException {
    GenreResponse response = createGenre.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping
  public ResponseEntity<List<GenreResponse>> get() throws NotFoundException {
    List<GenreResponse> genreResponses = getGenre.getAll();
    return ResponseEntity.ok().body(genreResponses);
  }

  @PutMapping("/{id}")
  public ResponseEntity<GenreResponse> update(@PathVariable(value = "id") Long id, @Valid @RequestBody GenreRequest request)
      throws NotFoundException {
    GenreResponse response = updateGenre.update(id, request);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
