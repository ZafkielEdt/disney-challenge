package com.challenge.disney.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MovieDTOBasic {

    private String image;
    private String title;
    private LocalDate creationDate;
}
