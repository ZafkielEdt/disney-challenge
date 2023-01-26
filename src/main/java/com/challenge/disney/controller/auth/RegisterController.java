package com.challenge.disney.controller.auth;

import com.challenge.disney.exception.EmailAlreadyExistsException;
import com.challenge.disney.exception.UsernameAlreadyExistsException;
import com.challenge.disney.model.request.auth.RegisterRequest;
import com.challenge.disney.model.response.auth.RegisterResponse;
import com.challenge.disney.service.abstraction.auth.Register;
import javax.management.relation.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
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
  public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request)
      throws UsernameAlreadyExistsException, EmailAlreadyExistsException, RoleNotFoundException {
    RegisterResponse response = register.register(request);
    return ResponseEntity.ok().body(response);
  }
}
