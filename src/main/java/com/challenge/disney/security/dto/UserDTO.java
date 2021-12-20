package com.challenge.disney.security.dto;

import com.challenge.disney.security.enums.Rol;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserDTO {

    @NotBlank(message = "Username is mandatory")
    private String username;
    @Min(value = 8)
    private String password;
    private Rol rol;
}
