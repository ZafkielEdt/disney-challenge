package com.challenge.app.model.request;

import jakarta.validation.constraints.NotBlank;

public record GenreRequest(
    @NotBlank(message = "Name can't be empty or null")
    String name,
    @NotBlank(message = "Image can't be empty or null")
    String image
) { }
