package com.challenge.app.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record FilmSeriesRequest(
    @NotBlank(message = "Title can't be empty or null")
    String title,
    @NotBlank(message = "ReleaseDate can't be empty or null")
    String releaseDate,
    @NotNull(message = "Rating can't be empty or null")
    Integer rating,
    @NotBlank(message = "Image can't be empty or null")
    String image,
    @NotNull(message = "Genre can't be empty or null")
    Set<Long> genreId
) {

}
