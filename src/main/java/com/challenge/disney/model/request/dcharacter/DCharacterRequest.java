package com.challenge.disney.model.request.dcharacter;

public record DCharacterRequest(
    String name,
    String age,
    String weight,
    String story,
    String image,
    String filmOrSerie) { }
