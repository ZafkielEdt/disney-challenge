package com.challenge.app.mapper;

import com.challenge.app.exception.NotFoundException;
import com.challenge.app.mapper.attribute.FilmSeriesAttributes;
import com.challenge.app.model.entity.DCharacter;
import com.challenge.app.model.entity.FilmSeries;
import com.challenge.app.model.entity.Genre;
import com.challenge.app.model.request.FilmSeriesRequest;
import com.challenge.app.model.response.FilmSeriesResponse;
import com.challenge.app.model.response.ListFilmSeriesResponse;
import com.challenge.app.repository.DCharacterRepository;
import com.challenge.app.repository.GenreRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FilmSeriesMapper {

  private final GenreRepository genreRepository;
  private final DCharacterRepository dCharacterRepository;
  private final DCharacterMapper dCharacterMapper;

  public FilmSeries map(FilmSeriesRequest request) throws NotFoundException {
    FilmSeries filmSeries = new FilmSeries();
    filmSeries.setTitle(request.title());
    filmSeries.setReleaseDate(LocalDate.parse(request.releaseDate()));
    filmSeries.setRating(request.rating());
    filmSeries.setImage(request.image());
    filmSeries.setGenres(request.genreId().stream()
        .map(id -> {
          try {
            return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Genre not found"));
          } catch (NotFoundException e) {
            throw new RuntimeException(e);
          }
        })
        .collect(Collectors.toSet()));
    return filmSeries;
  }

  public FilmSeriesResponse map(FilmSeries filmSeries, FilmSeriesAttributes... filmSeriesAttributes) {
    FilmSeriesResponse response = new FilmSeriesResponse();

    for (FilmSeriesAttributes attributes : filmSeriesAttributes) {
      switch (attributes) {
        case ID -> response.setId(filmSeries.getId());
        case TITLE -> response.setTitle(filmSeries.getTitle());
        case RELEASE_DATE -> response.setReleaseDate(filmSeries.getReleaseDate().toString());
        case RATING -> response.setRating(filmSeries.getRating());
        case IMAGE -> response.setImage(filmSeries.getImage());
        case GENRES -> response.setGenreNames(filmSeries.getGenres().stream()
            .map(Genre::getName).collect(Collectors.toList()));
        case CHARACTERS -> response.setCharacterNames(filmSeries.getCharacters().stream()
            .map(DCharacter::getName).collect(Collectors.toList()));
      }
    }
    return response;
  }

  public ListFilmSeriesResponse map(List<FilmSeries> filmSeries) {
    List<FilmSeriesResponse> filmSeriesResponseList = new ArrayList<>();
    ListFilmSeriesResponse listFilmSeriesResponse = new ListFilmSeriesResponse();

    for (FilmSeries fs : filmSeries) {
      FilmSeriesResponse response = map(fs,
          FilmSeriesAttributes.ID,
          FilmSeriesAttributes.TITLE,
          FilmSeriesAttributes.RELEASE_DATE,
          FilmSeriesAttributes.RATING,
          FilmSeriesAttributes.IMAGE,
          FilmSeriesAttributes.GENRES,
          FilmSeriesAttributes.CHARACTERS);
      filmSeriesResponseList.add(response);
    }

    listFilmSeriesResponse.setFilmSeriesResponses(filmSeriesResponseList);

    return listFilmSeriesResponse;
  }
}
