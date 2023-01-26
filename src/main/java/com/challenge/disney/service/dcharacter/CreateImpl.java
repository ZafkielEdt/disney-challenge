package com.challenge.disney.service.dcharacter;

import com.challenge.disney.exception.CharacterAlreadyExistsException;
import com.challenge.disney.mapper.DCharacterMapper;
import com.challenge.disney.model.entity.DCharacter;
import com.challenge.disney.model.request.dcharacter.DCharacterRequest;
import com.challenge.disney.model.response.dcharacter.DCharacterResponse;
import com.challenge.disney.repository.DCharacterRepository;
import com.challenge.disney.service.abstraction.dcharacter.Create;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateImpl implements Create {

  private final DCharacterRepository dCharacterRepository;

  private final DCharacterMapper dCharacterMapper;

  @Override
  public DCharacterResponse create(DCharacterRequest request)
      throws CharacterAlreadyExistsException {

    if (dCharacterRepository.existsByName(request.name())) {
      throw new CharacterAlreadyExistsException("Character already exists");
    }

    DCharacter dCharacter = dCharacterMapper.map(request);

    return dCharacterMapper.map(dCharacterRepository.save(dCharacter));
  }
}
