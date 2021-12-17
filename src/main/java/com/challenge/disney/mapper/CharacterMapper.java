package com.challenge.disney.mapper;

import com.challenge.disney.dtos.CharacterDTO;
import com.challenge.disney.dtos.CharacterDTOBasic;
import com.challenge.disney.entities.CharacterEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public CharacterMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CharacterEntity convertDTO2Entity(CharacterDTO dto) {

        return modelMapper.map(dto, CharacterEntity.class);
    }

    public CharacterDTO convertEntity2DTO(CharacterEntity entity) {

        return modelMapper.map(entity, CharacterDTO.class);
    }

    public List<CharacterDTOBasic> convertEntityList2DTOBasicList(List<CharacterEntity> entities) {

        List<CharacterDTOBasic> dtoBasics = new ArrayList<>();

        for(CharacterEntity entity : entities) {
            dtoBasics.add(modelMapper.map(entity,CharacterDTOBasic.class));
        }

        return dtoBasics;
    }

    public List<CharacterDTO> convertEntityList2DTOList(List<CharacterEntity> entities) {

        List<CharacterDTO> dtos = new ArrayList<>();

        for(CharacterEntity entity : entities) {
            dtos.add(convertEntity2DTO(entity));
        }

        return dtos;
    }

    public CharacterEntity updateCharacter(CharacterEntity entity, CharacterDTO dto) {

        if(!dto.getImage().isEmpty()) {
            entity.setImage(dto.getImage());
        }
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());
        if(!dto.getMovies().isEmpty()) {
            entity.setMovies(dto.getMovies());
        }

        return entity;
    }
}
