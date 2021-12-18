package com.challenge.disney.repositories.specification;

import com.challenge.disney.dtos.MovieFilterDTO;
import com.challenge.disney.entities.GenreEntity;
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
public class MovieSpecification {

    public Specification<MovieEntity> getByFilters(MovieFilterDTO filterDTO) {

        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(filterDTO.getTitle())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + filterDTO.getTitle().toLowerCase() + "%"
                        )
                );
            }

            if(!CollectionUtils.isEmpty(filterDTO.getGenreId())) {

                Join<MovieEntity, GenreEntity> join = root.join("genres", JoinType.INNER);
                Expression<String> genreId = join.get("idGenre");
                predicates.add(genreId.in(filterDTO.getGenreId()));
            }

            query.distinct(true);

            String orderByField = "creationDate";

            query.orderBy(
                    filterDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
