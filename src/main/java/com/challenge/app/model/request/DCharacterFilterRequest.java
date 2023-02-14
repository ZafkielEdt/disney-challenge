package com.challenge.app.model.request;

import java.util.Set;

public record DCharacterFilterRequest(
    String name,
    Long age,
    Set<Long> filmSeriesId
) {

}
