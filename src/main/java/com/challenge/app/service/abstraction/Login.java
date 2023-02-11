package com.challenge.app.service.abstraction;

import com.challenge.app.model.request.AuthCredentialsRequest;
import com.challenge.app.model.response.AuthCredentialsResponse;

public interface Login {

  AuthCredentialsResponse login(AuthCredentialsRequest request);
}
