package com.challenge.disney.common;


import com.challenge.disney.model.request.auth.LoginRequest;
import com.challenge.disney.model.request.auth.RegisterRequest;
import com.challenge.disney.model.response.auth.RegisterResponse;

public abstract class AbstractAuthUtils {


  public static RegisterRequest buildRegisterRequest() {
    RegisterRequest request = new RegisterRequest(
        "Dalet",
        "dalet@lorem.com",
        "12345678");
    return request;
  }

  public static LoginRequest buildLoginRequest() {
    LoginRequest request = new LoginRequest("Dalet", "12345678");
    return request;
  }

  public static RegisterResponse buildRegisterResponse() {
    RegisterResponse response = new RegisterResponse("Dalet", "dalet@lorem.com");
    return response;
  }
}
