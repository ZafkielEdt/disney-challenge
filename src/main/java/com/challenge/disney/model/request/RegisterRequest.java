package com.challenge.disney.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class RegisterRequest {

  @NotBlank(message = "Username can't be empty or null")
  private String username;

  @NotBlank(message = "Email can't be empty or null")
  private String email;

  @NotBlank(message = "Password can't be empty or null")
  @Length(min = 8, message = "Password min length is 8")
  private String password;
}
