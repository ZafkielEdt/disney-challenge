package com.challenge.app.repository;

import com.challenge.app.model.entity.DCharacter;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DCharacterRepository extends JpaRepository<DCharacter, Long> {

  Optional<DCharacter> findById(Long id);

  Boolean existsByName(String name);

  Page<DCharacter> findAll(Specification<DCharacter> specification, Pageable pageable);
}
