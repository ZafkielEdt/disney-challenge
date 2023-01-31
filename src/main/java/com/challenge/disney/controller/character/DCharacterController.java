package com.challenge.disney.controller.character;

import com.challenge.disney.exception.CharacterAlreadyExistsException;
import com.challenge.disney.model.request.dcharacter.DCharacterRequest;
import com.challenge.disney.model.response.dcharacter.DCharacterResponse;
import com.challenge.disney.service.abstraction.dcharacter.CreateCharacter;
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
@RequestMapping("/api/v2/characters")
@RequiredArgsConstructor
public class DCharacterController {

  private final CreateCharacter create;

  @PostMapping
  @PreAuthorize(value = "hasRole('ROLE_USER')")
  public ResponseEntity<DCharacterResponse> create(@Valid @RequestBody DCharacterRequest request)
      throws CharacterAlreadyExistsException {
    DCharacterResponse response = create.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
