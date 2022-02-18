package com.challenge.disney.controllers;

import com.challenge.disney.dtos.ApiErrorDTO;
import com.challenge.disney.errors.ServiceError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ServiceError.class})
    protected ResponseEntity<Object> handleConflictService(RuntimeException ex, WebRequest request) {

        ApiErrorDTO errorDTO = new ApiErrorDTO(
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
            List.of("Service Error")
        );

        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiErrorDTO errorDTO = new ApiErrorDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

        return handleExceptionInternal(ex, errorDTO, headers, errorDTO.getStatus(), request);
    }
}
