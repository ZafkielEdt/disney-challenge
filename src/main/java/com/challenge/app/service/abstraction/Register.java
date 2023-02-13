package com.challenge.app.service.abstraction;

import com.challenge.app.exception.ElementAlreadyExistsException;
import com.challenge.app.exception.NotFoundException;
import com.challenge.app.model.request.RegisterRequest;
import com.challenge.app.model.response.RegisterResponse;

public interface Register {

  RegisterResponse register(RegisterRequest request)
      throws ElementAlreadyExistsException, NotFoundException;
}
