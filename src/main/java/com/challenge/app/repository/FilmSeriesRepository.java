package com.challenge.app.repository;

import com.challenge.app.model.entity.FilmSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmSeriesRepository extends JpaRepository<FilmSeries, Long> {

}
