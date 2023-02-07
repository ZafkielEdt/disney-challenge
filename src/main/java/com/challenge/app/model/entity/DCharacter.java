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
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@Table(name = "DCHARACTER")
public class DCharacter {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "DCHARACTER_ID", unique = true, nullable = false)
  private Long id;

  @Column(name = "NAME", nullable = false, unique = true)
  private String name;

  @Column(name = "AGE", nullable = false)
  private Long age;

  @Column(name = "WEIGHT", nullable = false)
  private Long weight;

  @Column(name = "STORY", nullable = false)
  private String story;

  @Column(name = "IMAGE", nullable = false)
  private String image;

  @JoinTable(name = "DCHARACTER_FILM_SERIES",
      joinColumns = {@JoinColumn(name = "DCHARACTER_ID")},
      inverseJoinColumns = {@JoinColumn(name = "FILM_SERIES_ID")})
  @ManyToMany(cascade = CascadeType.PERSIST)
  private Set<FilmSeries> filmSeries;

  @Column(name = "TIMESTAMP")
  @CreationTimestamp
  private Timestamp timestamp;
}
