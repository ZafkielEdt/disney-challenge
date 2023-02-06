package com.challenge.app.mapper;

import com.challenge.app.model.entity.User;
import com.challenge.app.model.request.RegisterRequest;
import com.challenge.app.model.response.RegisterResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public User map(RegisterRequest request) {
    User user = new User();
    user.setUsername(request.username());
    user.setEmail(request.email());
    user.setSoftDelete(false);
    return user;
  }

  public RegisterResponse map(User user) {
    return new RegisterResponse(
      user.getUsername()
    );
  }
}
