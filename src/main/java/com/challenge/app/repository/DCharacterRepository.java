package com.challenge.app.repository;

import com.challenge.app.model.entity.DCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DCharacterRepository extends JpaRepository<DCharacter, Long> {

}
