package com.challenge.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ListFilmSeriesResponse(
    @JsonProperty("FilmSeries")
    List<FilmSeriesResponse> filmSeriesResponses
) {

}
