package com.challenge.disney.model.request;

import java.time.LocalDate;

public record FilmOrSeriesRequest(
    String title,
    LocalDate releaseDate,
    Integer rating,
    String image
) {

}
