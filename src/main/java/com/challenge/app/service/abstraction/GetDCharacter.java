package com.challenge.app.service.abstraction;

import com.challenge.app.model.response.DCharacterResponse;
import com.challenge.app.model.response.ListDCharacterResponse;

public interface GetDCharacter {

  DCharacterResponse get(Long id);

  ListDCharacterResponse getAll();
}
