package com.challenge.app.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "GENRE")
public class Genre {

  @Id
  @Column(name = "ID", nullable = false, unique = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "NAME", nullable = false, unique = true)
  private String name;

  @Column(name = "IMAGE", nullable = false)
  private String image;

  @ManyToMany(mappedBy = "genres", cascade = CascadeType.PERSIST)
  private Set<FilmSeries> filmSeries;

  @Column(name = "TIMESTAMP")
  @CreationTimestamp
  private Timestamp timestamp;
}
