package com.challenge.app.service.abstraction;

import com.challenge.app.exception.ElementAlreadyExistsException;
import com.challenge.app.model.request.GenreRequest;
import com.challenge.app.model.response.GenreResponse;

public interface CreateGenre {

  GenreResponse create(GenreRequest request)
      throws ElementAlreadyExistsException;
}
