package com.challenge.disney.repositories;

import com.challenge.disney.entities.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    Boolean existsCharacterModelByName(String name);

    List<CharacterEntity> findAll(Specification<CharacterEntity> character);
}
