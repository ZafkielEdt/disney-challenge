package com.challenge.disney.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "genre")
@SQLDelete(sql = "UPDATE genre SET deleted = True WHERE id_genre = ?")
@Where(clause = "deleted = false")
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_genre")
    private Long idGenre;

    private String image;

    @Column(nullable = false)
    private String name;

    private Boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "genres", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MovieEntity> movies = new ArrayList<>();
}
