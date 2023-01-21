package com.challenge.disney.service.auth;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import javax.management.relation.RoleNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
class RegisterImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserMapper userMapper;

  @Mock
  private BCryptPasswordEncoder encoder;

  @Mock
  private RoleRepository roleRepository;

  @InjectMocks
  private RegisterImpl register;

  @Test
  void register()
      throws UsernameAlreadyExistsException, EmailAlreadyExistsException, RoleNotFoundException {
    when(userRepository.existsByEmail(anyString())).thenReturn(false);
    when(userRepository.existsByUsername(anyString())).thenReturn(false);
    when(roleRepository.findByName(anyString())).thenReturn(Optional.of(buildRole()));
    when(userMapper.map(any(RegisterRequest.class))).thenReturn(buildUser());
    when(userRepository.save(any(User.class))).thenReturn(buildUser());
    when(userMapper.map(any(User.class))).thenReturn(buildResponse());


    RegisterResponse response = register.register(buildRequest());

    assertNotNull(response);
    assertNotNull(response.getUsername());
    assertNotNull(response.getEmail());
    assertEquals("Dalet", response.getUsername());
    assertEquals("dalet@lorem.com", response.getEmail());
  }

  private Role buildRole() {
    Role role = new Role();
    role.setId(1L);
    role.setName(RoleType.USER.getFullRoleName());
    role.setDescription(RoleType.USER.name());
    role.setTimestamp(Timestamp.from(Instant.now()));
    return role;
  }

  private RegisterRequest buildRequest() {
    RegisterRequest request = new RegisterRequest();
    request.setUsername("Dalet");
    request.setEmail("dalet@lorem.com");
    request.setPassword("12345678");
    return request;
  }

  private RegisterResponse buildResponse() {
    RegisterResponse response = new RegisterResponse();
    response.setUsername("Dalet");
    response.setEmail("dalet@lorem.com");
    return response;
  }

  private User buildUser() {
    User user = new User();
    user.setId(1L);
    user.setUsername("Dalet");
    user.setEmail("dalet@lorem.com");
    user.setPassword("12345678");
    user.setTimestamp(Timestamp.from(Instant.now()));
    user.setSoftDelete(false);
    return user;
  }
}