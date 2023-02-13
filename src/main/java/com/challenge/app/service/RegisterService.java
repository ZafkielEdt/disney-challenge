package com.challenge.app.service;

import com.challenge.app.config.security.RoleType;
import com.challenge.app.exception.ElementAlreadyExistsException;
import com.challenge.app.exception.NotFoundException;
import com.challenge.app.mapper.UserMapper;
import com.challenge.app.model.entity.User;
import com.challenge.app.model.request.RegisterRequest;
import com.challenge.app.model.response.RegisterResponse;
import com.challenge.app.repository.RoleRepository;
import com.challenge.app.repository.UserRepository;
import com.challenge.app.service.abstraction.Register;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService implements Register {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final UserMapper userMapper;
  private final BCryptPasswordEncoder encoder;

  @Override
  public RegisterResponse register(RegisterRequest request)
      throws ElementAlreadyExistsException, NotFoundException {

    existBy(request.username(), request.email());

    User user = userMapper.map(request);
    user.setPassword(encoder.encode(request.password()));
    user.setRoles(List.of(roleRepository.findByName(RoleType.USER.getFullRoleName())
        .orElseThrow(() -> new NotFoundException("Role not found"))));

    return userMapper.map(userRepository.save(user));
  }

  private void existBy(String username, String email)
      throws ElementAlreadyExistsException {
    if (userRepository.existsByUsername(username)) {
      throw new ElementAlreadyExistsException("Username already exists");
    }

    if (userRepository.existsByEmail(email)) {
      throw new ElementAlreadyExistsException("Email already exists");
    }
  }
}
