package com.example.demo.Exceptions;



public class PermissionException extends RuntimeException{

  public PermissionException() {
    super();
  }

  public PermissionException(String message) {
    super(message);
  }
}

