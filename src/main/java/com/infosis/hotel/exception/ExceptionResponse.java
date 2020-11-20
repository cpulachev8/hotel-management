package com.infosis.hotel.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {

  private LocalDateTime timestamp;
  private String message;
  private String details;

  @Override
  public String toString() {
    return "ExceptionResponse [timestamp=" + timestamp + ", message=" + message + ", details=" + details + "]";
  }

}

