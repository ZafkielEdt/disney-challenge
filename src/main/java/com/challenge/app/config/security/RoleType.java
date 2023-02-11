package com.challenge.app.config.security;

public enum RoleType {

  USER, ADMIN;

  private static final String ROLE_PREFIX = "ROLE_";

  public String getFullRoleName() {
    return ROLE_PREFIX + this.name();
  }
}
