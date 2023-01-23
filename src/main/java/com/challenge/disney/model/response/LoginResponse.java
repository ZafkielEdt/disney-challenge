package com.challenge.disney.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

  private String username;

  private String token;

  public LoginResponse() {
  }

  public LoginResponse(String username, String token) {
    this.username = username;
    this.token = token;
  }
}
