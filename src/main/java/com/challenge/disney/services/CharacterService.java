package com.challenge.disney.services;

import com.challenge.disney.dtos.CharacterDTO;
import com.challenge.disney.dtos.CharacterDTOBasic;
import com.challenge.disney.entities.CharacterEntity;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDTO createCharacter(CharacterDTO charDTO);

    List<CharacterDTOBasic> getAllCharacters();

    CharacterDTO updateCharacter(Long id, CharacterDTO characterDTO);

    void deleteCharacter(Long id);

    CharacterDTO getCharacter(Long id);

    List<CharacterDTO> getByFilters(String name, Integer age, Set<Long> filmId);

    List<CharacterEntity> getById(Set<Long> idChar);
}
