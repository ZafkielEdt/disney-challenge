package com.challenge.disney.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

  private String email;

  private String token;
}
