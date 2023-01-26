package com.challenge.disney.repository;

import com.challenge.disney.model.entity.DCharacter;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DCharacterRepository extends JpaRepository<DCharacter, Long> {

  Boolean existsByName(String name);
}
