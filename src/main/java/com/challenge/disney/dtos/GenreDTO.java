package com.challenge.disney.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GenreDTO {

    @NotBlank(message = "Name is mandatory")
    private String image;
    private String name;
}
