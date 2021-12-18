package com.challenge.disney.services;

import com.challenge.disney.dtos.MovieDTO;
import com.challenge.disney.dtos.MovieDTOBasic;

import java.util.List;
import java.util.Set;

public interface MovieService {

    MovieDTO createMovie(MovieDTO movieDTO);

    List<MovieDTOBasic> getAllMovies();

    MovieDTO getMovie(Long id);

    List<MovieDTO> getByFilters(String title, Set<Long> genre, String order);

    MovieDTO updateMovie(Long id, MovieDTO movieDTO);

    void deleteMovie(Long id);

    MovieDTO linkCharacter(Set<Long> idChar, Long idMovie);

    MovieDTO linkGenre(Set<Long> idGenre, Long idMovie);
}
