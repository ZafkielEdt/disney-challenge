package com.challenge.disney.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

  @NotBlank(message = "Username can't be empty or null")
  private String username;

  @NotBlank(message = "Password can't be empty or null")
  private String password;
}
