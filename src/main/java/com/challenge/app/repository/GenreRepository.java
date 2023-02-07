package com.challenge.app.repository;

import com.challenge.app.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

  Boolean existsByName(String name);
}
