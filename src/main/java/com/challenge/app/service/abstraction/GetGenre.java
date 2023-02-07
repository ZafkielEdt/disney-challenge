package com.challenge.app.service.abstraction;

import com.challenge.app.model.response.GenreResponse;
import java.util.Set;

public interface GetGenre {

  GenreResponse get(Long id);

  Set<GenreResponse> getAll();
}
