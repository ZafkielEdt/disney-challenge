package com.challenge.app.model.request;

import jakarta.validation.constraints.NotBlank;

public record FilmSeriesRequest(
    @NotBlank(message = "Title can't be empty or null")
    String title,
    @NotBlank(message = "ReleaseDate can't be empty or null")
    String releaseDate,
    @NotBlank(message = "Rating can't be empty or null")
    Integer rating,
    @NotBlank(message = "Image can't be empty or null")
    String image,
    @NotBlank(message = "Genre can't be empty or null")
    Long genreId
) {

}
