package com.challenge.disney.model.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegisterRequest (
    @NotBlank(message = "Username can't be empty or null") String username,
    @NotBlank(message = "Email can't be empty or null") String email,
    @NotBlank(message = "Password can't be empty or null")
    @Length(min = 8, message = "Password min length is 8")
    String password) { }
