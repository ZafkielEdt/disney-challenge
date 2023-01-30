package com.challenge.disney.model.request.dcharacter;

public record DCharacterRequest(
    String name,
    Long age,
    Long weight,
    String story,
    String image,
    String filmOrSerie) { }
