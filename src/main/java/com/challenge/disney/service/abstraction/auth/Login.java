package com.challenge.disney.service.abstraction.auth;

import com.challenge.disney.exception.InvalidCredentialsException;
import com.challenge.disney.model.request.auth.LoginRequest;
import com.challenge.disney.model.response.auth.LoginResponse;

public interface Login {

  LoginResponse login(LoginRequest request) throws InvalidCredentialsException;
}
