package com.challenge.app.model.response;


import java.util.List;

public record FilmSeriesResponse(
    String title,
    String releaseDate,
    Integer rating,
    String image,
    ListGenreResponse genreResponse
) {

}
