package com.challenge.disney.service.abstraction;

import com.challenge.disney.exception.InvalidCredentialsException;
import com.challenge.disney.model.request.LoginRequest;
import com.challenge.disney.model.response.LoginResponse;

public interface Login {

  LoginResponse login(LoginRequest request) throws InvalidCredentialsException;
}
