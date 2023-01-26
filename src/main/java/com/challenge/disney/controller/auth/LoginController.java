package com.challenge.disney.controller.auth;

import com.challenge.disney.exception.InvalidCredentialsException;
import com.challenge.disney.model.request.auth.LoginRequest;
import com.challenge.disney.model.response.auth.LoginResponse;
import com.challenge.disney.service.abstraction.auth.Login;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/login")
@RequiredArgsConstructor
public class LoginController {

  private final Login login;

  @PostMapping
  public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request)
      throws InvalidCredentialsException {
    LoginResponse response = login.login(request);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }
}
