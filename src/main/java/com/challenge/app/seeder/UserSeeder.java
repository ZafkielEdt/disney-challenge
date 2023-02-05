package com.challenge.app.seeder;

import com.challenge.app.config.security.RoleType;
import com.challenge.app.model.entity.Role;
import com.challenge.app.model.entity.User;
import com.challenge.app.repository.RoleRepository;
import com.challenge.app.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import javax.management.relation.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {

  private static final List<String> NAMES_ADMIN = List.of("Luitgard", "Christel", "Stefanie",
      "Oswald", "Ottomar", "Johann", "Moses", "Ianis", "Ashlyn", "Maria");
  private static final List<String> EMAILS_ADMIN = List.of("etluit12@gmail.com",
      "epchristel15@gmail.com", "jfstefanie5@gmail.com", "gkelsenbach10@gmail.com",
      "jjottomar9@gmail.com", "erleitner17@gmail.com", "hamoses0@gmail.com", "behoyt4@gmail.com",
      "adblanchette3@gmail.com", "dkpauling10@gmail.com");
  private static final List<String> PASSWORDS_ADMIN = List.of("t0400e3ps", "p00846e4p", "f94475j9f",
      "k15564g6k", "j654110j9j", "r05400e4r", "a987103h7a", "e62685b1e", "d25548a0d", "k97165d3k");
  private static final List<String> NAMES_USER = List.of("Kunigunde", "Marlies",
      "Lieselotte", "Carina", "Gabriele", "Gertrud", "Cecilia", "Sonje", "Gitte", "Auguste");
  private static final List<String> EMAILS_USER = List.of("bskunigunde18@gmail.com",
      "jpschell15@gmail.com", "airupel8@gmail.com", "cpcarina15@gmail.com",
      "hrgastelu17@gmail.com", "huweinmeier-r20@gmail.com", "esplatz18@gmail.com",
      "cysonje24@gmail.com", "gdgitte3@gmail.com", "clsarvott11@gmail.com");
  private static final List<String> PASSWORDS_USER = List.of("s30103b1s", "p96776j9p", "i117107a0i",
      "p09919c2p", "r80744h7r", "u83385h7u", "s23664e4s",
      "y59736c2y", "d30762g6d", "l22929c2l");

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final BCryptPasswordEncoder encoder;

  @Override
  public void run(String... args) throws Exception {
    createAdminUsers();
    createStandardUsers();
  }

  private void createAdminUsers() throws RoleNotFoundException {
    List<Role> roleAdmin = Collections.singletonList(
        roleRepository.findByName(RoleType.ADMIN.getFullRoleName())
            .orElseThrow(() -> new RoleNotFoundException("Role not found")));
    for (int index = 0; index < 10; index++) {
      createUser(NAMES_ADMIN.get(index),
          EMAILS_ADMIN.get(index),
          PASSWORDS_ADMIN.get(index),
          roleAdmin);
    }
  }

  private void createStandardUsers() throws RoleNotFoundException {
    List<Role> roleUser = Collections.singletonList(
        roleRepository.findByName(RoleType.USER.getFullRoleName())
            .orElseThrow(() -> new RoleNotFoundException("Role not found")));

    for (int index = 0; index < 10; index++) {
      createUser(NAMES_USER.get(index),
          EMAILS_USER.get(index),
          PASSWORDS_USER.get(index),
          roleUser);
    }
  }

  private void createUser(String username, String email, String password,
      List<Role> role) {
    User user = new User();
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(encoder.encode(password));
    user.setSoftDelete(false);
    userRepository.save(user);
    user.setRoles(role);
    userRepository.save(user);
  }
}
