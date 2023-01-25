package com.challenge.disney.service.auth;

import com.challenge.disney.common.mail.EmailUtils;
import com.challenge.disney.common.mail.template.RegisterEmailTemplate;
import com.challenge.disney.config.security.RoleType;
import com.challenge.disney.exception.EmailAlreadyExistsException;
import com.challenge.disney.exception.UsernameAlreadyExistsException;
import com.challenge.disney.mapper.UserMapper;
import com.challenge.disney.model.entity.Role;
import com.challenge.disney.model.entity.User;
import com.challenge.disney.model.request.RegisterRequest;
import com.challenge.disney.model.response.RegisterResponse;
import com.challenge.disney.repository.RoleRepository;
import com.challenge.disney.repository.UserRepository;
import com.challenge.disney.service.abstraction.Register;
import java.util.Set;
import javax.management.relation.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterImpl implements Register {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  private final BCryptPasswordEncoder encoder;

  private final RoleRepository roleRepository;

  private final EmailUtils emailUtils;

  @Override
  public RegisterResponse register(RegisterRequest request)
      throws EmailAlreadyExistsException, UsernameAlreadyExistsException, RoleNotFoundException {

    if (userRepository.existsByEmail(request.getEmail())) {
      throw new EmailAlreadyExistsException("Email already exists");
    }

    if (userRepository.existsByUsername(request.getUsername())) {
      throw new UsernameAlreadyExistsException("Username already exists");
    }

    Role role = roleRepository.findByName(RoleType.USER.getFullRoleName())
        .orElseThrow(() -> new RoleNotFoundException("Role not found"));

    User user = userMapper.map(request);
    user.setPassword(encoder.encode(user.getPassword()));
    user.setRoles(Set.of(role));
    user.setSoftDelete(false);

    sendRegisterEmail(request.getEmail());

    return userMapper.map(userRepository.save(user));
  }

  private void sendRegisterEmail(String emailTo) {
    try {
      emailUtils.send(getRegisterEmailTemplate(emailTo));
    } catch (Exception e) {
      log.error(e.getMessage());
    }
  }

  private RegisterEmailTemplate getRegisterEmailTemplate(String emailTo) {
    return new RegisterEmailTemplate("Disney Api", emailTo);
  }
}
