package com.challenge.app.controller;

import com.challenge.app.exception.ElementAlreadyExistsException;
import com.challenge.app.exception.NotFoundException;
import com.challenge.app.model.request.RegisterRequest;
import com.challenge.app.model.response.RegisterResponse;
import com.challenge.app.service.abstraction.Register;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/register")
@RequiredArgsConstructor
public class RegisterController {

  private final Register register;

  @PostMapping
  public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request)
      throws ElementAlreadyExistsException, NotFoundException {
    RegisterResponse response = register.register(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
