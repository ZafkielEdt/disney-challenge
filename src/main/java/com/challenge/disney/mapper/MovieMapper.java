package com.challenge.disney.mapper;

import com.challenge.disney.dtos.MovieDTO;
import com.challenge.disney.dtos.MovieDTOBasic;
import com.challenge.disney.entities.CharacterEntity;
import com.challenge.disney.entities.GenreEntity;
import com.challenge.disney.entities.MovieEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MovieMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public MovieMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public MovieEntity convertDTO2Entity(MovieDTO movieDTO) {
        return modelMapper.map(movieDTO, MovieEntity.class);
    }

    public MovieDTO convertEntity2DTO(MovieEntity entity) {
        return modelMapper.map(entity, MovieDTO.class);
    }

    public List<MovieDTOBasic> convertEntityList2DTOBasicList(List<MovieEntity> entities) {

        List<MovieDTOBasic> dtoBasics = new ArrayList<>();

        for(MovieEntity entity : entities) {
            dtoBasics.add(modelMapper.map(entity, MovieDTOBasic.class));
        }

        return dtoBasics;
    }

    public List<MovieDTO> convertEntityList2DTOList(List<MovieEntity> entities) {

        List<MovieDTO> dtos = new ArrayList<>();

        for(MovieEntity entity : entities) {
            dtos.add(modelMapper.map(entity, MovieDTO.class));
        }

        return dtos;
    }

    public MovieEntity updatedMovie(MovieEntity entity, MovieDTO movieDTO) {

        entity.setImage(movieDTO.getImage());
        entity.setTitle(movieDTO.getTitle());
        entity.setCreationDate(movieDTO.getCreationDate());
        entity.setRating(movieDTO.getRating());
        if(!movieDTO.getCharacters().isEmpty()) {
            entity.setCharacters(movieDTO.getCharacters());
        }
        if(!movieDTO.getGenres().isEmpty()) {
            entity.setGenres(movieDTO.getGenres());
        }

        return entity;
    }

    public void linkCharacter(List<CharacterEntity> characterEntities, MovieEntity movie) {

        Set<CharacterEntity> characters = movie.getCharacters();

        characters.addAll(characterEntities);

        movie.setCharacters(characters);
    }

    public void linkGenre(List<GenreEntity> genreEntities, MovieEntity movie) {

        Set<GenreEntity> genres = movie.getGenres();

        genres.addAll(genreEntities);

        movie.setGenres(genres);
    }
}
