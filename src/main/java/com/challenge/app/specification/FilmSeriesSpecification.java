package com.challenge.app.specification;

import com.challenge.app.model.entity.FilmSeries;
import com.challenge.app.model.entity.Genre;
import com.challenge.app.model.request.FilmSeriesFilterRequest;
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
public class FilmSeriesSpecification {
  public Specification<FilmSeries> getByFilters(FilmSeriesFilterRequest filterRequest) {

    return (root, query, criteriaBuilder) -> {

      List<Predicate> predicates = new ArrayList<>();

      if(StringUtils.hasLength(filterRequest.getTitle())) {
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("title")),
                "%" + filterRequest.getTitle().toLowerCase() + "%"
            )
        );
      }

      if(!CollectionUtils.isEmpty(filterRequest.getGenreId())) {

        Join<FilmSeries, Genre> join = root.join("genres",  JoinType.INNER);
        Expression<String> genreId = join.get("id");
        predicates.add(genreId.in(filterRequest.getGenreId()));
      }

      query.distinct(true);

      String orderByField = "timestamp";

      query.orderBy(
          filterRequest.isASC() ?
              criteriaBuilder.asc(root.get(orderByField)) :
              criteriaBuilder.desc(root.get(orderByField))
      );
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
