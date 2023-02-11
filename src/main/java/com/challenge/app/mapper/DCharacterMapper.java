package com.challenge.app.mapper;

import com.challenge.app.exception.NotFoundException;
import com.challenge.app.model.entity.DCharacter;
import com.challenge.app.model.request.DCharacterRequest;
import com.challenge.app.model.response.DCharacterResponse;
import com.challenge.app.model.response.ListDCharacterResponse;
import com.challenge.app.model.response.ListFilmSeriesResponse;
import com.challenge.app.repository.FilmSeriesRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DCharacterMapper {

  private final FilmSeriesRepository filmSeriesRepository;

  private final FilmSeriesMapper filmSeriesMapper;

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

  public DCharacterResponse map(DCharacter dCharacter) {
    return new DCharacterResponse(
        dCharacter.getName(),
        dCharacter.getAge(),
        dCharacter.getWeight(),
        dCharacter.getStory(),
        dCharacter.getImage(),
        filmSeriesMapper.map(dCharacter.getFilmSeries().stream().toList())
    );
  }

  public ListDCharacterResponse map(List<DCharacter> dCharacters) {
    List<DCharacterResponse> responses = new ArrayList<>();

    for (DCharacter character : dCharacters) {
      DCharacterResponse response = map(character);
      responses.add(response);
    }

    return new ListDCharacterResponse(
        responses
    );
  }
}
