package com.challenge.disney.services.impl;

import com.challenge.disney.dtos.MovieDTO;
import com.challenge.disney.dtos.MovieDTOBasic;
import com.challenge.disney.dtos.MovieFilterDTO;
import com.challenge.disney.entities.CharacterEntity;
import com.challenge.disney.entities.GenreEntity;
import com.challenge.disney.entities.MovieEntity;
import com.challenge.disney.errors.ServiceError;
import com.challenge.disney.mapper.MovieMapper;
import com.challenge.disney.repositories.MovieRepository;
import com.challenge.disney.repositories.specification.MovieSpecification;
import com.challenge.disney.services.CharacterService;
import com.challenge.disney.services.GenreService;
import com.challenge.disney.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;

    private final MovieSpecification movieSpecification;

    private final CharacterService characterService;

    private final GenreService genreService;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository,
                            MovieMapper movieMapper,
                            MovieSpecification movieSpecification,
                            CharacterService characterService,
                            GenreService genreService) {
        this.movieRepository = movieRepository;
        this.movieMapper = movieMapper;
        this.movieSpecification = movieSpecification;
        this.characterService = characterService;
        this.genreService = genreService;
    }

    // Create
    @Override
    @Transactional
    public MovieDTO createMovie(MovieDTO movieDTO) {

        Boolean exist = movieRepository.existsMovieModelByTitle(movieDTO.getTitle());

        if(exist) {
            throw new ServiceError("Movie already exist");
        }

        MovieEntity entity = movieMapper.convertDTO2Entity(movieDTO);

        movieRepository.save(entity);

        return movieMapper.convertEntity2DTO(entity);
    }

    // Get All Movies
    @Override
    @Transactional(readOnly = true)
    public List<MovieDTOBasic> getAllMovies() {

        List<MovieEntity> entities = movieRepository.findAll();

        return movieMapper.convertEntityList2DTOBasicList(entities);
    }

    // Get one Movie
    @Override
    @Transactional(readOnly = true)
    public MovieDTO getMovie(Long id) {

        Optional<MovieEntity> entity = movieRepository.findById(id);

        if(entity.isEmpty()) {
            throw new ServiceError("Movie not found");
        }

        MovieEntity movieEntity = entity.get();

        return movieMapper.convertEntity2DTO(movieEntity);
    }

    // Update
    @Override
    @Transactional
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {

        Optional<MovieEntity> entity = movieRepository.findById(id);

        if(entity.isEmpty()) {
            throw new ServiceError("Movie not found");
        }

        MovieEntity movieEntity = entity.get();

        movieEntity = movieMapper.updatedMovie(movieEntity, movieDTO);

        movieRepository.save(movieEntity);

        return movieMapper.convertEntity2DTO(movieEntity);
    }

    // Delete
    @Override
    @Transactional
    public void deleteMovie(Long id) {

        Optional<MovieEntity> entity = movieRepository.findById(id);

        if(entity.isEmpty()) {
            throw new ServiceError("Movie not found");
        }

        movieRepository.deleteById(id);
    }

    // Get By Filters
    @Override
    @Transactional(readOnly = true)
    public List<MovieDTO> getByFilters(String title, Set<Long> genre, String order) {

        MovieFilterDTO filterDTO = new MovieFilterDTO(title,genre,order);

        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filterDTO));

        if(entities.isEmpty()) {
            throw new ServiceError("Elements not found");
        }

        return movieMapper.convertEntityList2DTOList(entities);
    }

    // Link Character
    @Override
    @Transactional
    public MovieDTO linkCharacter(Set<Long> idChar, Long idMovie) {

        List<CharacterEntity> characterEntities = characterService.getById(idChar);

        Optional<MovieEntity> entity = movieRepository.findById(idMovie);

        if (characterEntities.isEmpty() || entity.isEmpty()) {
            throw new ServiceError("Elements not found");
        }

        MovieEntity movieEntity = entity.get();

        movieMapper.linkCharacter(characterEntities, movieEntity);

        movieRepository.save(movieEntity);

        return movieMapper.convertEntity2DTO(movieEntity);
    }

    @Override
    @Transactional
    public MovieDTO linkGenre(Set<Long> idGenre, Long idMovie) {

        List<GenreEntity> genreEntities = genreService.findById(idGenre);

        Optional<MovieEntity> entity = movieRepository.findById(idMovie);

        if (genreEntities.isEmpty() || entity.isEmpty()) {
            throw new ServiceError("Elements not found");
        }

        MovieEntity movieEntity = entity.get();

        movieMapper.linkGenre(genreEntities, movieEntity);

        movieRepository.save(movieEntity);

        return movieMapper.convertEntity2DTO(movieEntity);
    }
}
