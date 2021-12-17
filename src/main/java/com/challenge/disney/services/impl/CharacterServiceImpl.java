package com.challenge.disney.services.impl;

import com.challenge.disney.dtos.CharacterDTO;
import com.challenge.disney.dtos.CharacterDTOBasic;
import com.challenge.disney.dtos.CharacterFilterDTO;
import com.challenge.disney.entities.CharacterEntity;
import com.challenge.disney.errors.ServiceError;
import com.challenge.disney.mapper.CharacterMapper;
import com.challenge.disney.repositories.CharacterRepository;
import com.challenge.disney.repositories.specification.CharacterSpecification;
import com.challenge.disney.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    private final CharacterMapper mapper;

    private final CharacterSpecification characterSpecification;

    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepository,
                                CharacterMapper mapper,
                                CharacterSpecification characterSpecification) {
        this.characterRepository = characterRepository;
        this.mapper = mapper;
        this.characterSpecification = characterSpecification;
    }

    // Create
    @Override
    @Transactional
    public CharacterDTO createCharacter(CharacterDTO charDTO) {

        Boolean exist = characterRepository.existsCharacterModelByName(charDTO.getName());

        if(exist) {
            throw new ServiceError("Character already exists");
        }

        CharacterEntity entity = mapper.convertDTO2Entity(charDTO);

        characterRepository.save(entity);

        return mapper.convertEntity2DTO(entity);
    }

    // Get All Characters
    @Override
    @Transactional(readOnly = true)
    public List<CharacterDTOBasic> getAllCharacters() {

        List<CharacterEntity> entities = characterRepository.findAll();

        List<CharacterDTOBasic> dtoBasics = mapper.convertEntityList2DTOBasicList(entities);

        return dtoBasics;
    }

    // Get one character
    @Override
    @Transactional(readOnly = true)
    public CharacterDTO getCharacter(Long id) {

        Optional<CharacterEntity> entity = characterRepository.findById(id);

        if(entity.isEmpty()) {
            throw new ServiceError("Element not found");
        }

        CharacterEntity charEntity = entity.get();

        return mapper.convertEntity2DTO(charEntity);
    }

    // Update
    @Override
    @Transactional
    public CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO) {

        Optional<CharacterEntity> entity = characterRepository.findById(id);

        if(entity.isEmpty()) {
            throw new ServiceError("Element not found");
        }

        CharacterEntity charEntity = entity.get();

        charEntity = mapper.updateCharacter(charEntity, characterDTO);

        characterRepository.save(charEntity);

        return mapper.convertEntity2DTO(charEntity);

    }

    // Delete
    @Override
    @Transactional
    public void deleteCharacter(Long id) {

        Optional<CharacterEntity> entity = characterRepository.findById(id);

        if(entity.isEmpty()) {
            throw new ServiceError("Character not found");
        }
        characterRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> idMovie) {

        CharacterFilterDTO filtersDTO = new CharacterFilterDTO(name, age, idMovie);

        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));

        if(entities.isEmpty()) {
            throw new ServiceError("Elements not found");
        }

        return mapper.convertEntityList2DTOList(entities);
    }

    @Override
    public List<CharacterEntity> getById(Set<Long> idChar) {

        return characterRepository.findAllById(idChar);
    }
}
