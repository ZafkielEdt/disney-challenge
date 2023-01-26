package com.challenge.disney.mapper;

import com.challenge.disney.model.entity.DCharacter;
import com.challenge.disney.model.request.dcharacter.DCharacterRequest;
import com.challenge.disney.model.response.dcharacter.DCharacterResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DCharacterMapper {

  private final ModelMapper modelMapper;

  public DCharacter map(DCharacterRequest request) {
    return modelMapper.map(request, DCharacter.class);
  }

  public DCharacterResponse map(DCharacter dCharacter) {
    return modelMapper.map(dCharacter, DCharacterResponse.class);
  }
}
