package com.challenge.app.service.abstraction;

import com.challenge.app.exception.GenreNotFoundException;

public interface DeleteGenre {

  void delete(Long id) throws GenreNotFoundException;
}
