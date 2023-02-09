package com.challenge.app.service.abstraction;

import com.challenge.app.exception.GenreNotFoundException;
import com.challenge.app.model.request.GenreRequest;
import com.challenge.app.model.response.GenreResponse;

public interface UpdateGenre {

  GenreResponse update(Long id, GenreRequest genreRequest) throws GenreNotFoundException;
}
