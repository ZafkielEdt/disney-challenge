package com.challenge.disney.model.response;

import java.time.LocalDate;

public record FilmOrSeriesResponse(
  String title,
  LocalDate releaseDate,
  Integer rating,
  String image
) { }
