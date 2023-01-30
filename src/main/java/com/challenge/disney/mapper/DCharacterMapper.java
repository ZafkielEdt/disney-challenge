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
    DCharacter dCharacter = new DCharacter();
    dCharacter.setName(request.name());
    dCharacter.setAge(request.age());
    dCharacter.setStory(request.story());
    dCharacter.setImage(request.image());
    dCharacter.setWeight(request.weight());
    return dCharacter;
  }

  public DCharacterResponse map(DCharacter dCharacter) {
    return new DCharacterResponse(
        dCharacter.getName(),
        dCharacter.getAge(),
        dCharacter.getWeight(),
        dCharacter.getStory(),
        dCharacter.getStory()
    );
  }
}
