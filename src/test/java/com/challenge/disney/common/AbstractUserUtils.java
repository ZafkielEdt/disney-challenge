package com.challenge.disney.common;

import static com.challenge.disney.common.AbstractRoleUtils.buildRole;

import com.challenge.disney.model.entity.User;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

public abstract class AbstractUserUtils {

  public static User buildUser() {
    User user = new User();
    user.setId(1L);
    user.setUsername("Dalet");
    user.setEmail("dalet@lorem.com");
    user.setPassword("12345678");
    user.setRoles(Set.of(buildRole()));
    user.setTimestamp(Timestamp.from(Instant.now()));
    user.setSoftDelete(false);
    return user;
  }
}
