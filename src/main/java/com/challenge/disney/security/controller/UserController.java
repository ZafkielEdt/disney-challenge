package com.challenge.disney.security.controller;

import com.challenge.disney.security.dto.AuthenticationRequest;
import com.challenge.disney.security.dto.AuthenticationResponse;
import com.challenge.disney.security.dto.UserDTO;
import com.challenge.disney.security.service.JwtUtil;
import com.challenge.disney.security.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl,
                          JwtUtil jwtUtil,
                          AuthenticationManager authenticationManager) {
        this.userServiceImpl = userServiceImpl;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserDTO userDTO) {

        userServiceImpl.saveUser(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails userDetails = userServiceImpl.loadUserByUsername(request.getUsername());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
