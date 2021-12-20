package com.challenge.disney.security.mapper;

import com.challenge.disney.security.dto.UserDTO;
import com.challenge.disney.security.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper mapper;

    @Autowired
    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public UserEntity convertDTO2Entity(UserDTO userDTO) {

        return mapper.map(userDTO, UserEntity.class);
    }
}
