package com.challenge.disney.service.auth;

import static com.challenge.disney.common.AbstractAuthUtils.buildLoginRequest;
import static com.challenge.disney.common.AbstractUserUtils.buildUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.challenge.disney.common.JwtUtils;
import com.challenge.disney.exception.InvalidCredentialsException;
import com.challenge.disney.model.response.LoginResponse;
import com.challenge.disney.repository.UserRepository;
import java.util.Collection;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@ExtendWith(MockitoExtension.class)
class LoginImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private JwtUtils jwtUtils;

  @InjectMocks
  private LoginImpl login;

  @Test
  void loginShouldReturnLoginResponse() throws InvalidCredentialsException {
    when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(buildUser()));
    when(jwtUtils.generateToken(buildUser().getUsername(),
        (Collection<SimpleGrantedAuthority>) buildUser().getAuthorities())).thenReturn(anyString());
    LoginResponse response = login.login(buildLoginRequest());

    assertNotNull(response);
    assertEquals("Dalet", response.getUsername());
    assertNotNull(response.getToken());
  }

  @Test
  void loginShouldThrownInvalidCredentialsException() {
    when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

    InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, ()
        -> {
      LoginResponse response = login.login(buildLoginRequest());
    });

    assertNotNull(exception);
    assertEquals(InvalidCredentialsException.class, exception.getClass());
    assertEquals("Invalid username or password", exception.getMessage());
  }
}