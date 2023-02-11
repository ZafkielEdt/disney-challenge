package com.challenge.app.service.abstraction;

import com.challenge.app.exception.NotFoundException;

public interface DeleteFilmSeries {

  void delete(Long id) throws NotFoundException;
}
