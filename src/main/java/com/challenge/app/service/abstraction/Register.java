package com.challenge.app.service.abstraction;

import com.challenge.app.exception.UserAlreadyExistsException;
import com.challenge.app.model.request.RegisterRequest;
import com.challenge.app.model.response.RegisterResponse;
import javax.management.relation.RoleNotFoundException;

public interface Register {

  RegisterResponse register(RegisterRequest request)
      throws UserAlreadyExistsException, RoleNotFoundException;
}
