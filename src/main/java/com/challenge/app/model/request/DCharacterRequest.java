package com.challenge.app.model.request;

public record DCharacterRequest(
    String name,
    Long age,
    Long weight,
    String story,
    String image,
    Long filmSeriesId
) {

}
