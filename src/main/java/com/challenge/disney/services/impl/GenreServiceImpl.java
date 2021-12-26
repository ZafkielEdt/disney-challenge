package com.challenge.disney.services.impl;

import com.challenge.disney.dtos.GenreDTO;
import com.challenge.disney.entities.GenreEntity;
import com.challenge.disney.errors.ServiceError;
import com.challenge.disney.mapper.GenreMapper;
import com.challenge.disney.repositories.GenreRepository;
import com.challenge.disney.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    // Create
    @Override
    @Transactional
    public GenreDTO createGenre(GenreDTO genreDTO) throws ServiceError {

        Boolean exist = genreRepository.existsGenreModelByName(genreDTO.getName());

        if(exist) {
            throw new ServiceError("Genre already exist");
        }

        GenreEntity genre = genreMapper.convertDTO2Entity(genreDTO);

        genreRepository.save(genre);

        return genreMapper.convertEntity2DTO(genre);
    }

    // Get All Genres
    @Override
    @Transactional(readOnly = true)
    public List<GenreDTO> getAllGenres() throws ServiceError {

        List<GenreEntity> entities = genreRepository.findAll();

        if(entities.isEmpty()) {
            throw new ServiceError("Any genre found");
        }

        return genreMapper.convertEntityList2DTOList(entities);
    }

    // Update
    @Override
    @Transactional
    public GenreDTO updateGenre(Long id, GenreDTO dto) throws ServiceError {

        Optional<GenreEntity> entity = genreRepository.findById(id);

        if (entity.isEmpty()) {
            throw new ServiceError("Element not found");
        }

        GenreEntity genreEntity = entity.get();

        genreEntity = genreMapper.updateGenre(dto, genreEntity);

        genreRepository.save(genreEntity);

        return genreMapper.convertEntity2DTO(genreEntity);
    }

    // Delete
    @Override
    @Transactional
    public void deleteGenre(Long id) throws ServiceError {

        Optional<GenreEntity> entity = genreRepository.findById(id);

        if (entity.isEmpty()) {
            throw new ServiceError("Element not found");
        }

        genreRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GenreEntity> findById(Set<Long> idGenre) {

        return genreRepository.findAllById(idGenre);
    }
}
