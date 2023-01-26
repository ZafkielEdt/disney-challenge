package com.challenge.disney.model.response.dcharacter;

public record DCharacterResponse(
    String name,
    String age,
    String weight,
    String story,
    String image,
    String filmOrSerie) { }
