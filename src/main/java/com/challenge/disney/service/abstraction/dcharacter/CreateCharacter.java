package com.challenge.disney.service.abstraction.dcharacter;

import com.challenge.disney.exception.CharacterAlreadyExistsException;
import com.challenge.disney.model.request.dcharacter.DCharacterRequest;
import com.challenge.disney.model.response.dcharacter.DCharacterResponse;

public interface CreateCharacter {

  DCharacterResponse create(DCharacterRequest request) throws CharacterAlreadyExistsException;
}
