package com.challenge.disney.dtos;

import com.challenge.disney.entities.CharacterEntity;
import com.challenge.disney.entities.GenreEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class MovieDTO {

    private String image;
    @NotBlank(message = "Title is mandatory")
    private String title;
    private LocalDate creationDate;
    @Min(value = 0)
    private Integer rating;
    private Set<GenreEntity> genres = new HashSet<>();
    private Set<CharacterEntity> characters = new HashSet<>();
}
