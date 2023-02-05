package com.challenge.app.seeder;

import com.challenge.app.config.security.RoleType;
import com.challenge.app.model.entity.Role;
import com.challenge.app.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

  private final RoleRepository roleRepository;

  @Override
  @Order(1)
  public void run(String... args) throws Exception {
    createRole(RoleType.ADMIN);
    createRole(RoleType.USER);
  }

  private void seedRoleTable() {
    if (roleRepository.count() == 0) {
      createRole(RoleType.ADMIN);
      createRole(RoleType.USER);
    }
  }

  private void createRole(RoleType roleType) {
    Role role = new Role();
    role.setName(roleType.getFullRoleName());
    role.setDescription(roleType.name());
    roleRepository.save(role);
  }
}
