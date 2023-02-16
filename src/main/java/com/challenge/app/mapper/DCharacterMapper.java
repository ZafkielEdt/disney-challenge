package com.challenge.app.mapper;

import com.challenge.app.exception.NotFoundException;
import com.challenge.app.mapper.attribute.DCharacterAttributes;
import com.challenge.app.model.entity.DCharacter;
import com.challenge.app.model.entity.FilmSeries;
import com.challenge.app.model.request.DCharacterRequest;
import com.challenge.app.model.response.DCharacterResponse;
import com.challenge.app.model.response.ListDCharacterResponse;
import com.challenge.app.repository.FilmSeriesRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DCharacterMapper {

  private final FilmSeriesRepository filmSeriesRepository;

  public DCharacter map(DCharacterRequest request) throws NotFoundException {

    DCharacter dCharacter = new DCharacter();

    dCharacter.setName(request.name());
    dCharacter.setAge(request.age());
    dCharacter.setWeight(request.weight());
    dCharacter.setStory(request.story());
    dCharacter.setImage(request.image());
    dCharacter.setFilmSeries(Set.of(filmSeriesRepository.findById(request.filmSeriesId())
        .orElseThrow(() -> new NotFoundException("Film or series not found"))));
    return dCharacter;
  }

  public DCharacterResponse map(DCharacter dCharacter, DCharacterAttributes... dCharacterAttributes) {
    DCharacterResponse response = new DCharacterResponse();

    for (DCharacterAttributes attributes : dCharacterAttributes) {
      switch (attributes) {
        case ID -> response.setId(dCharacter.getId());
        case NAME -> response.setName(dCharacter.getName());
        case AGE -> response.setAge(dCharacter.getAge());
        case WEIGHT -> response.setWeight(dCharacter.getWeight());
        case STORY -> response.setStory(dCharacter.getStory());
        case IMAGE -> response.setImage(dCharacter.getImage());
        case FILM_SERIES -> response.setFilmsSeriesTitles(dCharacter.getFilmSeries()
            .stream().map(FilmSeries::getTitle).collect(Collectors.toList()));
      }
    }

    return response;
  }

  public ListDCharacterResponse map(List<DCharacter> dCharacters) {
    List<DCharacterResponse> responses = new ArrayList<>();

    for (DCharacter character : dCharacters) {
      DCharacterResponse response = map(character,
          DCharacterAttributes.ID,
          DCharacterAttributes.NAME,
          DCharacterAttributes.AGE,
          DCharacterAttributes.WEIGHT,
          DCharacterAttributes.STORY,
          DCharacterAttributes.IMAGE,
          DCharacterAttributes.FILM_SERIES);
      responses.add(response);
    }

    ListDCharacterResponse listDCharacterResponse = new ListDCharacterResponse();
    listDCharacterResponse.setDCharacterResponses(responses);

    return listDCharacterResponse;
  }
}
