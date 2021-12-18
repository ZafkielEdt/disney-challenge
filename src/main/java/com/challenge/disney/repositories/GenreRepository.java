package com.challenge.disney.repositories;

import com.challenge.disney.entities.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

    Boolean existsGenreModelByName(String name);
}
