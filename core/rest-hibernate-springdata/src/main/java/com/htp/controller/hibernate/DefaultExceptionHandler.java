package com.htp.controller.hibernate;

import com.htp.controller.messages.ErrorMessage;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler {

  private static final Logger LOG = Logger.getLogger(DefaultExceptionHandler.class);

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorMessage> handleNoSuchEntityException(
      MethodArgumentNotValidException e) {
    LOG.error(e.getMessage(), e);
    return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException e) {
    LOG.error(e.getMessage(), e);
    return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<ErrorMessage> handleNPException(NullPointerException e) {
    LOG.error(e.getMessage(), e);
    return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> handleOthersException(Exception e) {
    LOG.error(e.getMessage(), e);
    return new ResponseEntity<>(new ErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
