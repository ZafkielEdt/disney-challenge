package com.challenge.disney.mapper;

import com.challenge.disney.dtos.GenreDTO;
import com.challenge.disney.entities.GenreEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public GenreMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public GenreEntity convertDTO2Entity(GenreDTO genreDTO) {

        return modelMapper.map(genreDTO, GenreEntity.class);
    }

    public GenreDTO convertEntity2DTO(GenreEntity genre) {

        return modelMapper.map(genre, GenreDTO.class);
    }

    public List<GenreDTO> convertEntityList2DTOList(List<GenreEntity> entities) {

        List<GenreDTO> dtos = new ArrayList<>();

        for(GenreEntity entity : entities) {
            dtos.add(modelMapper.map(entity,GenreDTO.class));
        }
        return dtos;
    }

    public GenreEntity updateGenre(GenreDTO dto, GenreEntity genreEntity) {

        genreEntity.setImage(dto.getImage());
        genreEntity.setName(dto.getName());

        return genreEntity;
    }
}
