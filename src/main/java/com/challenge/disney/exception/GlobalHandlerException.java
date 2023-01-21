package com.challenge.disney.exception;

import java.sql.Timestamp;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {

  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(
      EmailAlreadyExistsException e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(EmailAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleUsernameAlreadyExistsException(
      UsernameAlreadyExistsException e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  private ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message) {
    ErrorResponse error = new ErrorResponse();
    error.setStatus(httpStatus.value());
    error.add(message);
    error.setTimestamp(Timestamp.from(Instant.now()));
    return error;
  }
}
