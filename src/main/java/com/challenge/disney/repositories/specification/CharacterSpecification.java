package com.challenge.disney.repositories.specification;

import com.challenge.disney.dtos.CharacterFilterDTO;
import com.challenge.disney.entities.CharacterEntity;
import com.challenge.disney.entities.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> getByFilters(CharacterFilterDTO filterDTO) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(filterDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filterDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            if(filterDTO.getAge() != null && filterDTO.getAge() > 0) {
                predicates.add(
                        criteriaBuilder.equal(root.get("age"), filterDTO.getAge())
                );
            }

            if(!CollectionUtils.isEmpty(filterDTO.getMovieId())) {
                Join<CharacterEntity, MovieEntity> join = root.join("movie", JoinType.INNER);
                Expression<String> movieId = join.get("idMovie");
                predicates.add(movieId.in(filterDTO.getMovieId()));
            }

            // remove duplicate
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
