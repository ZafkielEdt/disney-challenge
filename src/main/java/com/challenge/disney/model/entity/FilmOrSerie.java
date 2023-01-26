package com.challenge.disney.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
public class FilmOrSerie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false, unique = true)
  private Long id;

  @Column(name = "TITLE", nullable = false, unique = true)
  private String title;

  @Column(name = "RELEASE_DATE", nullable = false)
  private LocalDate releaseDate;

  @Column(name = "RATING", nullable = false)
  private Integer rating;

  @Column(name = "IMAGE", nullable = false)
  private String image;

  @ManyToMany(mappedBy = "filmOrSeries", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  private Set<DCharacter> characters;

  @JoinTable(name = "FILM_SERIE_GENRE",
      joinColumns = {@JoinColumn(name = "FILM_SERIE_ID")},
      inverseJoinColumns = {@JoinColumn(name = "GENRE_ID")})
  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  private Set<Genre> genres;

  @Column(name = "TIMESTAMP")
  @CreationTimestamp
  private Timestamp timestamp;
}
