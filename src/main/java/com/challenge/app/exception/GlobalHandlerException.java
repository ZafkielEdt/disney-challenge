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

  private ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message) {
    return new ErrorResponse(
        httpStatus.value(),
        Collections.singletonList(message),
        Timestamp.from(Instant.now())
    );
  }
}
