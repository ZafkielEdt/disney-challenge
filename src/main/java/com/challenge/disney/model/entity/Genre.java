package com.challenge.disney.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.sql.Timestamp;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
public class Genre {

  @Id
  @Column(name = "ID", nullable = false, unique = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "IMAGE", nullable = false)
  private String image;

  @ManyToMany(mappedBy = "genres", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  private Set<FilmOrSeries> filmOrSeries;

  @Column(name = "TIMESTAMP")
  @CreationTimestamp
  private Timestamp timestamp;
}
