package com.challenge.app.controller;

import com.challenge.app.model.request.AuthCredentialsRequest;
import com.challenge.app.model.response.AuthCredentialsResponse;
import com.challenge.app.service.abstraction.Login;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
  public ResponseEntity<AuthCredentialsResponse> login(@Valid @RequestBody AuthCredentialsRequest request) {
    AuthCredentialsResponse response = login.login(request);
    return ResponseEntity.ok().body(response);
  }
}
