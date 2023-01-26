package com.challenge.disney.service.abstraction.auth;

import com.challenge.disney.exception.EmailAlreadyExistsException;
import com.challenge.disney.exception.UsernameAlreadyExistsException;
import com.challenge.disney.model.request.auth.RegisterRequest;
import com.challenge.disney.model.response.auth.RegisterResponse;
import javax.management.relation.RoleNotFoundException;

public interface Register {

  RegisterResponse register(RegisterRequest request)
      throws EmailAlreadyExistsException, UsernameAlreadyExistsException, RoleNotFoundException;
}
