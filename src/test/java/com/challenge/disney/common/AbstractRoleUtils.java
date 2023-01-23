package com.challenge.disney.common;

import com.challenge.disney.config.security.RoleType;
import com.challenge.disney.model.entity.Role;
import java.sql.Timestamp;
import java.time.Instant;

public abstract class AbstractRoleUtils {

  public static Role buildRole() {
    Role role = new Role();
    role.setId(1L);
    role.setName(RoleType.USER.getFullRoleName());
    role.setDescription(RoleType.USER.name());
    role.setTimestamp(Timestamp.from(Instant.now()));
    return role;
  }
}
