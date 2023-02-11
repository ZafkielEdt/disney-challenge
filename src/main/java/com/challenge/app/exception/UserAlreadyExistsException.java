package com.challenge.app.exception;

public class UserAlreadyExistsException extends Exception{

  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
