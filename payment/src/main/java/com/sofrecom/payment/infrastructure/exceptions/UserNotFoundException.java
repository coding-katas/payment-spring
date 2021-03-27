package com.sofrecom.payment.infrastructure.exceptions;

public class UserNotFoundException extends Throwable {
  public UserNotFoundException(String msg) {
    super(msg);
  }
}
