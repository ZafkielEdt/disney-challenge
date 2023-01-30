package com.challenge.disney.model.response.dcharacter;

public record DCharacterResponse(
    String name,
    Long age,
    Long weight,
    String story,
    String image) { }
