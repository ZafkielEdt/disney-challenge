package com.challenge.disney.config.security;

public enum RoleType {

  ADMIN, USER;

  private static final String ROLE_PREFIX = "ROLE_";

  private String getFullRoleName() {
    return ROLE_PREFIX + this.name();
  }
}
