package com.challenge.disney.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "character_disney")
@SQLDelete(sql = "UPDATE character_disney SET deleted = True WHERE id_char = ?")
@Where(clause = "deleted = false")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_char")
    private Long idChar;

    private String image;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Integer weight;

    @Column(nullable = false)
    private String story;

    @Column(nullable = false)
    private Boolean deleted = Boolean.FALSE;

    @JsonIgnore
    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
    private Set<MovieEntity> movies = new HashSet<>();
}
