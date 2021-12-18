package com.challenge.disney.services;

import com.challenge.disney.dtos.GenreDTO;
import com.challenge.disney.entities.GenreEntity;

import java.util.List;
import java.util.Set;

public interface GenreService {

    GenreDTO createGenre(GenreDTO genreDTO);

    List<GenreDTO> getAllGenres();

    GenreDTO updateGenre(Long id, GenreDTO dto);

    void deleteGenre(Long id);

    List<GenreEntity> findById(Set<Long> idGenre);
}
