package com.challenge.app.service.abstraction;

import com.challenge.app.exception.GenreNotFoundException;
import com.challenge.app.model.response.GenreResponse;
import java.util.List;

public interface GetGenre {

  List<GenreResponse> getAll() throws GenreNotFoundException;
}
