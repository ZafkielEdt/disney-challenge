package com.challenge.app.specification;

import com.challenge.app.model.entity.DCharacter;
import com.challenge.app.model.entity.FilmSeries;
import com.challenge.app.model.request.DCharacterFilterRequest;
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

  public Specification<DCharacter> getByFilters(DCharacterFilterRequest dCharacterFilterRequest) {

    return (root, query, criteriaBuilder) -> {

      List<Predicate> predicates = new ArrayList<>();

      if(StringUtils.hasLength(dCharacterFilterRequest.name())) {
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("name")),
                "%" + dCharacterFilterRequest.name().toLowerCase() + "%"
            )
        );
      }

      if(dCharacterFilterRequest.age() != null && dCharacterFilterRequest.age() > 0) {
        predicates.add(
            criteriaBuilder.equal(root.get("age"), dCharacterFilterRequest.age())
        );
      }

      if(!CollectionUtils.isEmpty(dCharacterFilterRequest.filmSeriesId())) {
        Join<DCharacter, FilmSeries> join = root.join("filmSeries", JoinType.INNER);
        Expression<String> movieId = join.get("id");
        predicates.add(movieId.in(dCharacterFilterRequest.filmSeriesId()));
      }

      // remove duplicate
      query.distinct(true);

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
