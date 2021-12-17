package com.challenge.disney.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CharacterFilterDTO {

    private String name;
    private Integer age;
    private Set<Long> movieId;

    public CharacterFilterDTO(String name, Integer age, Set<Long> movieId) {
        this.name = name;
        this.age = age;
        this.movieId = movieId;
    }
}
