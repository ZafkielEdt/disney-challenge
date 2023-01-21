package com.challenge.disney.service.abstraction;

import com.challenge.disney.exception.EmailAlreadyExistsException;
import com.challenge.disney.exception.UsernameAlreadyExistsException;
import com.challenge.disney.model.request.RegisterRequest;
import com.challenge.disney.model.response.RegisterResponse;
import javax.management.relation.RoleNotFoundException;

public interface Register {

  RegisterResponse register(RegisterRequest request)
      throws EmailAlreadyExistsException, UsernameAlreadyExistsException, RoleNotFoundException;
}