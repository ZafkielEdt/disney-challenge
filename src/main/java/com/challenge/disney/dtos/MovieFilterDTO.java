package com.challenge.disney.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class MovieFilterDTO {

    private String image;
    private String title;
    private String creationDate;
    private String ranking;
    private Set<Long> genreId = new HashSet<>();
    private String order;

    public MovieFilterDTO(String title, Set<Long> genreId, String order) {
        this.title = title;
        this.genreId = genreId;
        this.order = order;
    }

    public boolean isASC() {
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC() {
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
}
