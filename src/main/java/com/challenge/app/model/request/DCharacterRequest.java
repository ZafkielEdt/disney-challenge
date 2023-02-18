package com.challenge.app.model.request;

import java.util.Set;

public record DCharacterRequest(
    String name,
    Long age,
    Long weight,
    String story,
    String image,
    Set<Long> filmSeriesId
) {

}
