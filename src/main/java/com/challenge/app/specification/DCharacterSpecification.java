package com.challenge.app.specification;

import com.challenge.app.model.entity.DCharacter;
import com.challenge.app.model.entity.FilmSeries;
import com.challenge.app.model.request.DCharacterFilter;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class DCharacterSpecification {

  public Specification<DCharacter> getByFilters(DCharacterFilter dCharacterFilter) {

    return (root, query, criteriaBuilder) -> {

      List<Predicate> predicates = new ArrayList<>();

      if(StringUtils.hasLength(dCharacterFilter.name())) {
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("name")),
                "%" + dCharacterFilter.name().toLowerCase() + "%"
            )
        );
      }

      if(dCharacterFilter.age() != null && dCharacterFilter.age() > 0) {
        predicates.add(
            criteriaBuilder.equal(root.get("age"), dCharacterFilter.age())
        );
      }

      if(!CollectionUtils.isEmpty(dCharacterFilter.filmSeriesId())) {
        Join<DCharacter, FilmSeries> join = root.join("filmSeries", JoinType.INNER);
        Expression<String> movieId = join.get("id");
        predicates.add(movieId.in(dCharacterFilter.filmSeriesId()));
      }

      // remove duplicate
      query.distinct(true);

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
