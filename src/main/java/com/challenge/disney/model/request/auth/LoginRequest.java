package com.challenge.disney.model.request.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest (
    @NotBlank(message = "Username can't be empty or null") String username,
    @NotBlank(message = "Password can't be empty or null")String password) { }
