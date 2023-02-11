package com.challenge.app.mapper;

import com.challenge.app.exception.NotFoundException;
import com.challenge.app.model.entity.FilmSeries;
import com.challenge.app.model.request.FilmSeriesRequest;
import com.challenge.app.model.response.FilmSeriesResponse;
import com.challenge.app.model.response.ListFilmSeriesResponse;
import com.challenge.app.model.response.ListGenreResponse;
import com.challenge.app.repository.FilmSeriesRepository;
import com.challenge.app.repository.GenreRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FilmSeriesMapper {

  private final GenreRepository genreRepository;

  private final GenreMapper genreMapper;

  public FilmSeries map(FilmSeriesRequest request) throws NotFoundException {
    FilmSeries filmSeries = new FilmSeries();
    filmSeries.setTitle(request.title());
    filmSeries.setReleaseDate(LocalDate.parse(request.releaseDate()));
    filmSeries.setRating(request.rating());
    filmSeries.setImage(request.image());
    filmSeries.setGenres(Set.of(genreRepository.findById(request.genreId()).
        orElseThrow(() -> new NotFoundException("Genre not found"))));
    return filmSeries;
  }

  public FilmSeriesResponse map(FilmSeries filmSeries) {
    return new FilmSeriesResponse(
        filmSeries.getTitle(),
        filmSeries.getReleaseDate().toString(),
        filmSeries.getRating(),
        filmSeries.getImage(),
        new ListGenreResponse(
            genreMapper.map(filmSeries.getGenres().stream().toList())
        )
    );
  }

  public ListFilmSeriesResponse map(List<FilmSeries> filmSeries) {
    List<FilmSeriesResponse> filmSeriesResponseList = new ArrayList<>();

    for (FilmSeries fs : filmSeries) {
      FilmSeriesResponse response = map(fs);
      filmSeriesResponseList.add(response);
    }

    return new ListFilmSeriesResponse(
        filmSeriesResponseList
    );
  }
}
