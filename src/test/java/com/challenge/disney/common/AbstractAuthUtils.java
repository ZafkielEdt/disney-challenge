package com.challenge.disney.common;


import com.challenge.disney.model.request.LoginRequest;
import com.challenge.disney.model.request.RegisterRequest;
import com.challenge.disney.model.response.RegisterResponse;

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
