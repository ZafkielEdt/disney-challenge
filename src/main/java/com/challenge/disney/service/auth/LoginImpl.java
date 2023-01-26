package com.challenge.disney.service.auth;

import com.challenge.disney.common.JwtUtils;
import com.challenge.disney.exception.InvalidCredentialsException;
import com.challenge.disney.model.entity.User;
import com.challenge.disney.model.request.LoginRequest;
import com.challenge.disney.model.response.LoginResponse;
import com.challenge.disney.repository.UserRepository;
import com.challenge.disney.service.abstraction.Login;
import java.util.Collection;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginImpl implements Login {

  private final UserRepository userRepository;

  private final AuthenticationManager authenticationManager;

  private final JwtUtils jwtUtils;

  @Override
  public LoginResponse login(LoginRequest request) throws InvalidCredentialsException {

    Optional<User> optionalUser = userRepository.findByUsername(request.username());


    if (optionalUser.isEmpty()) {
      throw new InvalidCredentialsException("Invalid username or password");
    }


    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.username(), request.password()));

    User user = optionalUser.get();

    return new LoginResponse(user.getUsername(),
        jwtUtils.generateToken(user.getUsername(),
            (Collection<SimpleGrantedAuthority>) user.getAuthorities()));
  }
}
