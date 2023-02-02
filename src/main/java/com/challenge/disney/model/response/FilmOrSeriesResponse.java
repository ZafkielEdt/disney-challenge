package com.challenge.disney.model.response;

import java.time.LocalDate;

public record FilmOrSeriesResponse(
  String title,
  String releaseDate,
  Integer rating,
  String image
) { }
