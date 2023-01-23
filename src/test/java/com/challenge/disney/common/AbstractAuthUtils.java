package com.challenge.disney.common;


import com.challenge.disney.model.request.LoginRequest;
import com.challenge.disney.model.request.RegisterRequest;
import com.challenge.disney.model.response.RegisterResponse;

public abstract class AbstractAuthUtils {


  public static RegisterRequest buildRegisterRequest() {
    RegisterRequest request = new RegisterRequest();
    request.setUsername("Dalet");
    request.setEmail("dalet@lorem.com");
    request.setPassword("12345678");
    return request;
  }

  public static LoginRequest buildLoginRequest() {
    LoginRequest request = new LoginRequest();
    request.setUsername("Dalet");
    request.setPassword("12345678");
    return request;
  }

  public static RegisterResponse buildRegisterResponse() {
    RegisterResponse response = new RegisterResponse();
    response.setUsername("Dalet");
    response.setEmail("dalet@lorem.com");
    return response;
  }
}
