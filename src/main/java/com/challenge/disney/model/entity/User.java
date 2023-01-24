package com.challenge.disney.model.entity;

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
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@Table(name = "USER")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID", nullable = false, unique = true)
  private Long id;

  @Column(name = "USERNAME", nullable = false, unique = true)
  private String username;

  @Column(name = "EMAIL", nullable = false, unique = true)
  private String email;

  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @Column(name = "SOFT_DELETE", nullable = false)
  private Boolean softDelete;

  @CreationTimestamp
  @Column(name = "TIMESTAMP")
  private Timestamp timestamp;

  @JoinTable(name = "USER_ROL",
      joinColumns = {@JoinColumn(name = "USER_ID", nullable = false)},
      inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", nullable = false)})
  @ManyToMany(cascade = CascadeType.PERSIST)
  private Set<Role> roles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    return this.getRoles()
        .stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .collect(Collectors.toSet());
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return !this.softDelete;
  }
}
