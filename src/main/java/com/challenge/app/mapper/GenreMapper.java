package com.challenge.app.mapper;

import com.challenge.app.model.entity.Genre;
import com.challenge.app.model.request.GenreRequest;
import com.challenge.app.model.response.GenreResponse;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

  public Genre map(GenreRequest request) {
    Genre genre = new Genre();
    genre.setName(request.name());
    genre.setImage(request.image());
    return genre;
  }

  public GenreResponse map(Genre genre) {
    return new GenreResponse(
        genre.getName(),
        genre.getImage()
    );
  }
}
