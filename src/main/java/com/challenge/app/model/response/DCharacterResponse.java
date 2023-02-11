package com.challenge.app.model.response;

public record DCharacterResponse(
    String name,
    Long age,
    Long weight,
    String story,
    String image,
    ListFilmSeriesResponse filmSeriesResponse
) {

}
