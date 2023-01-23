package com.challenge.disney.service.auth;

import static com.challenge.disney.common.AbstractAuthUtils.buildRegisterRequest;
import static com.challenge.disney.common.AbstractAuthUtils.buildRegisterResponse;
import static com.challenge.disney.common.AbstractRoleUtils.buildRole;
import static com.challenge.disney.common.AbstractUserUtils.buildUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


import com.challenge.disney.exception.EmailAlreadyExistsException;
import com.challenge.disney.exception.UsernameAlreadyExistsException;
import com.challenge.disney.mapper.UserMapper;
import com.challenge.disney.model.entity.User;
import com.challenge.disney.model.request.RegisterRequest;
import com.challenge.disney.model.response.RegisterResponse;
import com.challenge.disney.repository.RoleRepository;
import com.challenge.disney.repository.UserRepository;
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
  void registerShouldReturnRegisterResponse()
      throws UsernameAlreadyExistsException, EmailAlreadyExistsException, RoleNotFoundException {
    when(userRepository.existsByEmail(anyString())).thenReturn(false);
    when(userRepository.existsByUsername(anyString())).thenReturn(false);
    when(roleRepository.findByName(anyString())).thenReturn(Optional.of(buildRole()));
    when(userMapper.map(any(RegisterRequest.class))).thenReturn(buildUser());
    when(userRepository.save(any(User.class))).thenReturn(buildUser());
    when(userMapper.map(any(User.class))).thenReturn(buildRegisterResponse());


    RegisterResponse response = register.register(buildRegisterRequest());

    assertNotNull(response);
    assertNotNull(response.getUsername());
    assertNotNull(response.getEmail());
    assertEquals("Dalet", response.getUsername());
    assertEquals("dalet@lorem.com", response.getEmail());
  }

  @Test
  void registerShouldThrowEmailAlreadyExistsException() {
    when(userRepository.existsByEmail(anyString())).thenReturn(true);

    EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class,
        () -> {
          RegisterResponse response = register.register(buildRegisterRequest());
        });

    assertNotNull(exception);
    assertEquals(EmailAlreadyExistsException.class, exception.getClass());
    assertEquals("Email already exists", exception.getMessage());
  }
}