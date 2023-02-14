package com.challenge.app.mapper;

import com.challenge.app.exception.NotFoundException;
import com.challenge.app.model.entity.DCharacter;
import com.challenge.app.model.entity.FilmSeries;
import com.challenge.app.model.request.DCharacterRequest;
import com.challenge.app.model.response.DCharacterResponse;
import com.challenge.app.model.response.FilmSeriesResponse;
import com.challenge.app.model.response.ListDCharacterResponse;
import com.challenge.app.model.response.ListFilmSeriesResponse;
import com.challenge.app.model.response.ListGenreResponse;
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

  private final GenreMapper genreMapper;

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
    ListFilmSeriesResponse listFilmSeriesResponse = new ListFilmSeriesResponse();
    listFilmSeriesResponse.setFilmSeriesResponses(dCharacter.getFilmSeries().stream().map(
        this::mapTo
    ).collect(Collectors.toList()));

    return new DCharacterResponse(
        dCharacter.getName(),
        dCharacter.getAge(),
        dCharacter.getWeight(),
        dCharacter.getStory(),
        dCharacter.getImage(),
        listFilmSeriesResponse
    );
  }

  private FilmSeriesResponse mapTo(FilmSeries filmSeries) {
    return new FilmSeriesResponse(
        filmSeries.getTitle(),
        filmSeries.getReleaseDate().toString(),
        filmSeries.getRating(),
        filmSeries.getImage(),
        new ListGenreResponse(
            genreMapper.map(filmSeries.getGenres().stream().toList())
        ),
        null
    );
  }

  public ListDCharacterResponse map(List<DCharacter> dCharacters) {
    List<DCharacterResponse> responses = new ArrayList<>();

    for (DCharacter character : dCharacters) {
      DCharacterResponse response = map(character);
      responses.add(response);
    }

    ListDCharacterResponse listDCharacterResponse = new ListDCharacterResponse();
    listDCharacterResponse.setDCharacterResponses(responses);

    return listDCharacterResponse;
  }
}
