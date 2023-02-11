package com.challenge.app.service.abstraction;

import com.challenge.app.exception.ElementAlreadyExistsException;
import com.challenge.app.exception.NotFoundException;
import com.challenge.app.model.request.DCharacterRequest;
import com.challenge.app.model.response.DCharacterResponse;

public interface CreateDCharacter {

  DCharacterResponse create(DCharacterRequest request)
      throws ElementAlreadyExistsException, NotFoundException;
}
