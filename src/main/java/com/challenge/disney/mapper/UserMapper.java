package com.challenge.disney.mapper;

import com.challenge.disney.model.entity.User;
import com.challenge.disney.model.request.auth.RegisterRequest;
import com.challenge.disney.model.response.auth.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

  private final ModelMapper mapper;

  public User map(RegisterRequest request) {
    return mapper.map(request, User.class);
  }

  public RegisterResponse map(User user) {
    return mapper.map(user, RegisterResponse.class);
  }
}
