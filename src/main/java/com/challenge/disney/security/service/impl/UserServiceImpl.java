package com.challenge.disney.security.service.impl;

import com.challenge.disney.errors.ServiceError;
import com.challenge.disney.security.dto.UserDTO;
import com.challenge.disney.security.entity.UserEntity;
import com.challenge.disney.security.enums.Rol;
import com.challenge.disney.security.mapper.UserMapper;
import com.challenge.disney.security.repository.UserRepository;
import com.challenge.disney.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder encoder;

    private final String MESSAGE = "The user with email %s not exist.";

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public void saveUser(UserDTO userDTO) {

        Boolean exist = userRepository.existsUserEntityByUsername(userDTO.getUsername());

        if (exist) {
            throw new ServiceError("User already exists");
        }

        if(!userRepository.existsUserEntityByRol(Rol.ADMIN)) {
            userDTO.setRol(Rol.ADMIN);
        } else {
            userDTO.setRol(Rol.USER);
        }

        userDTO.setPassword(encoder.encode(userDTO.getPassword()));

        UserEntity user = userMapper.convertDTO2Entity(userDTO);

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findUserEntityByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException(String.format(MESSAGE, username)));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRol());

        return new User(user.getUsername(), user.getPassword(), Collections.singletonList(authority));
    }
}
