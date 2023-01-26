package com.challenge.disney.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "ROLE")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ROLE_ID", nullable = false, unique = true)
  private Long id;

  @Column(name = "NAME", nullable = false, unique = true)
  private String name;

  @Column(name = "DESCRIPTION", nullable = false)
  private String description;

  @ManyToMany(mappedBy = "roles", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  private Set<User> users;

  @CreationTimestamp
  @Column(name = "TIMESTAMP")
  private Timestamp timestamp;
}
