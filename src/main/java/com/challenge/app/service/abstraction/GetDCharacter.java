package com.challenge.app.service.abstraction;

import com.challenge.app.exception.NotFoundException;
import com.challenge.app.model.response.DCharacterResponse;
import com.challenge.app.model.response.ListDCharacterResponse;
import java.util.Set;
import org.springframework.data.domain.Pageable;

public interface GetDCharacter {

  DCharacterResponse get(Long id) throws NotFoundException;

  ListDCharacterResponse getAll() throws NotFoundException;

  ListDCharacterResponse getBy(String name, Long age, Set<Long> filmSeriesId, Pageable pageable)
      throws NotFoundException;
}
