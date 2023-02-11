package com.challenge.app.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@Table(name = "FILM_SERIES")
public class FilmSeries {

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

  @ManyToMany(mappedBy = "filmSeries", cascade = CascadeType.PERSIST)
  private Set<DCharacter> characters;

  @JoinTable(name = "FILM_SERIES_GENRE",
      joinColumns = {@JoinColumn(name = "FILM_SERIES_ID")},
      inverseJoinColumns = {@JoinColumn(name = "GENRE_ID")})
  @ManyToMany(cascade = CascadeType.PERSIST)
  private Set<Genre> genres;

  @Column(name = "TIMESTAMP")
  @CreationTimestamp
  private Timestamp timestamp;
}
