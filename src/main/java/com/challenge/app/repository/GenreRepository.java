package com.challenge.app.repository;

import com.challenge.app.model.entity.Genre;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

  List<Genre> findAll();

  Boolean existsByName(String name);
}
