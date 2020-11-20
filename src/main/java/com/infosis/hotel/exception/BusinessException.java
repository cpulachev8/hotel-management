package com.infosis.hotel.exception;

public class BusinessException extends Exception {

  private static final long serialVersionUID = -1924476522408445506L;
  private final Integer status;

  public BusinessException(String message, Integer status) {
    super(message);
    this.status = status;
  }

  public Integer getStatus() {
    return status;
  }

}

