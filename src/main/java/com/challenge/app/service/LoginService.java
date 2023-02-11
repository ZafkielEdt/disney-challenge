package com.challenge.app.service;

import com.challenge.app.common.JwtUtils;
import com.challenge.app.model.entity.User;
import com.challenge.app.model.request.AuthCredentialsRequest;
import com.challenge.app.model.response.AuthCredentialsResponse;
import com.challenge.app.repository.UserRepository;
import com.challenge.app.service.abstraction.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements Login {

  private final UserRepository userRepository;
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;

  @Override
  public AuthCredentialsResponse login(AuthCredentialsRequest request) {

    User user = userRepository.findByUsername(request.username())
        .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

    authenticationManager.
        authenticate(
            new UsernamePasswordAuthenticationToken(
                request.username(),
                request.password()
            )
        );

    return new AuthCredentialsResponse(
        user.getUsername(),
        jwtUtils.generateToken(
            user.getUsername(),
            user.getAuthorities()
        )
    );
  }
}
