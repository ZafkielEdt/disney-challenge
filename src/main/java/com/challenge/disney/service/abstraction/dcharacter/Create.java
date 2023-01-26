package com.challenge.disney.service.abstraction.dcharacter;

import com.challenge.disney.model.request.dcharacter.DCharacterRequest;
import com.challenge.disney.model.response.dcharacter.DCharacterResponse;

public interface Create {

  DCharacterResponse create(DCharacterRequest request);
}
