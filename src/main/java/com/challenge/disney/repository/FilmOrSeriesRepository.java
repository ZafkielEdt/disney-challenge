package com.challenge.disney.repository;

import com.challenge.disney.model.entity.FilmOrSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmOrSeriesRepository extends JpaRepository<FilmOrSeries, Long> {

  Boolean existsByTitle(String title);
}
