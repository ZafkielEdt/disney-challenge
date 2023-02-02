package com.challenge.disney.model.request;

import java.time.LocalDate;

public record FilmOrSeriesRequest(
    String title,
    String releaseDate,
    Integer rating,
    String image
) {

}
