package com.challenge.disney.dtos;

import com.challenge.disney.entities.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CharacterDTO {

    private String image;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Min(value = 0)
    private Integer age;
    @Min(value = 0)
    private Integer weight;
    @NotBlank(message = "Story is mandatory")
    private String story;
    private Set<MovieEntity> movies = new HashSet<>();
}
