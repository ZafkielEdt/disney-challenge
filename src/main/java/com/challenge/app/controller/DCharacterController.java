package com.challenge.app.controller;

import com.challenge.app.common.PaginatedResultsRetrieved;
import com.challenge.app.exception.ElementAlreadyExistsException;
import com.challenge.app.exception.NotFoundException;
import com.challenge.app.model.request.DCharacterRequest;
import com.challenge.app.model.response.DCharacterResponse;
import com.challenge.app.model.response.ListDCharacterResponse;
import com.challenge.app.service.abstraction.CreateDCharacter;
import com.challenge.app.service.abstraction.DeleteDCharacter;
import com.challenge.app.service.abstraction.GetDCharacter;
import com.challenge.app.service.abstraction.UpdateDCharacter;
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
@RequestMapping("/api/v2/dcharacters")
@RequiredArgsConstructor
public class DCharacterController {

  private final CreateDCharacter createDCharacter;
  private final GetDCharacter getDCharacter;
  private final UpdateDCharacter updateDCharacter;
  private final DeleteDCharacter deleteDCharacter;
  private final PaginatedResultsRetrieved resultsRetrieved;

  @PostMapping
  public ResponseEntity<DCharacterResponse> create(@Valid @RequestBody DCharacterRequest request)
      throws ElementAlreadyExistsException, NotFoundException {
    DCharacterResponse response = createDCharacter.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DCharacterResponse> get(@PathVariable(value = "id") Long id)
      throws NotFoundException {
    DCharacterResponse response = getDCharacter.get(id);
    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<ListDCharacterResponse> getAll() throws NotFoundException {
    ListDCharacterResponse response = getDCharacter.getAll();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/filter")
  public ResponseEntity<ListDCharacterResponse> getAllBy(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) Long age,
      @RequestParam(required = false) Set<Long> filmSeriesId,
      Pageable pageable, UriComponentsBuilder uriComponentsBuilder,
      HttpServletResponse servletResponse
  ) throws NotFoundException {
    ListDCharacterResponse response = getDCharacter.getBy(name, age, filmSeriesId, pageable);

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
  public ResponseEntity<DCharacterResponse> update(@PathVariable(value = "id") Long id,
      @Valid @RequestBody DCharacterRequest request) throws NotFoundException {
    DCharacterResponse response = updateDCharacter.update(id, request);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) throws NotFoundException {
    deleteDCharacter.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
