package com.challenge.app.exception;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException {

  @ExceptionHandler(InsufficientPermissionsException.class)
  public ResponseEntity<ErrorResponse> handleInsufficientPermissionsException(InsufficientPermissionsException e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.FORBIDDEN, e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(GenreAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> handleGenreAlreadyExistsException(GenreAlreadyExistsException e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleGenreNotFoundException(NotFoundException e) {
    ErrorResponse errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  private ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message) {
    return new ErrorResponse(
        httpStatus.value(),
        Collections.singletonList(message),
        Timestamp.from(Instant.now())
    );
  }
}
