package com.challenge.app.model.response;

import java.util.List;

public record ListFilmSeriesResponse(
    List<FilmSeriesResponse> filmSeriesResponses
) {

}
