package com.challenge.app.model.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record AuthCredentialsRequest(
    @NotBlank(message = "Username can't be empty or null.")
    String username,
    @NotBlank(message = "Password can't be empty or null.")
    @Length(message = "Password should contain 8 characters as less.")
    String password
) { }
