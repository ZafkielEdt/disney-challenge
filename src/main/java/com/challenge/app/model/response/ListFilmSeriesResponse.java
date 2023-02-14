package com.challenge.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListFilmSeriesResponse extends PaginationResponse{
    @JsonProperty("FilmSeries")
    List<FilmSeriesResponse> filmSeriesResponses;
}
