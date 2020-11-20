package com.infosis.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RestController
class ExceptionHandlerCustomized extends ResponseEntityExceptionHandler {

  @ExceptionHandler(UsernameNotFoundException.class)
  public final ResponseEntity<Object> handleUsernameNotFoundException
          (UsernameNotFoundException ex, WebRequest request){

    ExceptionResponse exceptionResponse =
            new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
                    request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(AuthenticationException.class)
  public final ResponseEntity<Object> handleAuthenticationException
          (AuthenticationException ex, WebRequest request){

    ExceptionResponse exceptionResponse =
            new ExceptionResponse(LocalDateTime.now(), "Credenciales incorrectas",
                    request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public final ResponseEntity<Object> handleAccessDeniedException
          (AccessDeniedException ex, WebRequest request){

    ExceptionResponse exceptionResponse =
            new ExceptionResponse(LocalDateTime.now(), "Acceso denegado",
                    request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(BusinessException.class)
  public final ResponseEntity<Object> handleBusinessException
          (BusinessException ex, WebRequest request){

    ExceptionResponse exceptionResponse =
            new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
                    request.getDescription(false));

    return new ResponseEntity<>(exceptionResponse, HttpStatus.resolve(ex.getStatus()));
  }

}

