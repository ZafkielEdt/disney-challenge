package com.challenge.disney.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiErrorDTO {

    private HttpStatus status;
    private String message;
    private List<String> errors;
}
