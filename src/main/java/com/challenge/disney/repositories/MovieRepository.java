package com.challenge.disney.repositories;

import com.challenge.disney.entities.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    Boolean existsMovieModelByTitle(String title);

    List<MovieEntity> findAll(Specification<MovieEntity> movieSpecification);
}
