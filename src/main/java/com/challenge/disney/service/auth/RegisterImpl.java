package com.challenge.disney.service.auth;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterImpl implements Register {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  private final BCryptPasswordEncoder encoder;

  private final RoleRepository roleRepository;

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

    return userMapper.map(userRepository.save(user));
  }
}
