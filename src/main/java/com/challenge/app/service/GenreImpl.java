package com.challenge.app.service;

import com.challenge.app.exception.GenreAlreadyExistsException;
import com.challenge.app.mapper.GenreMapper;
import com.challenge.app.model.entity.Genre;
import com.challenge.app.model.request.GenreRequest;
import com.challenge.app.model.response.GenreResponse;
import com.challenge.app.repository.GenreRepository;
import com.challenge.app.service.abstraction.CreateGenre;
import com.challenge.app.service.abstraction.DeleteGenre;
import com.challenge.app.service.abstraction.GetGenre;
import com.challenge.app.service.abstraction.UpdateGenre;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreImpl implements CreateGenre, GetGenre, UpdateGenre, DeleteGenre {

  private final GenreRepository genreRepository;

  private final GenreMapper genreMapper;

  @Override
  public GenreResponse create(GenreRequest request) throws GenreAlreadyExistsException {

    if (genreRepository.existsByName(request.name())) {
      throw new GenreAlreadyExistsException("Genre already exists");
    }

    Genre genre = genreMapper.map(request);

    return genreMapper.map(genreRepository.save(genre));
  }

  @Override
  public GenreResponse get(Long id) {
    return null;
  }

  @Override
  public Set<GenreResponse> getAll() {
    return null;
  }

  @Override
  public GenreResponse update(Long id, GenreRequest genreRequest) {
    return null;
  }

  @Override
  public void delete(Long id) {

  }
}
