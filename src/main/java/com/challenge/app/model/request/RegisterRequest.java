package com.challenge.app.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegisterRequest(
    @NotBlank(message = "Username can't be empty or null")
    String username,
    @Email(message = "Email can't be empty or null")
    String email,
    @Length(message = "Password should contain 8 characters as less.")
    String password
) {

}
