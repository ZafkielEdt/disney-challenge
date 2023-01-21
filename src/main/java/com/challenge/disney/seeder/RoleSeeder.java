package com.challenge.disney.seeder;

import com.challenge.disney.config.security.RoleType;
import com.challenge.disney.model.entity.Role;
import com.challenge.disney.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

  private final RoleRepository roleRepository;

  @Override
  public void run(String... args) throws Exception {
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
