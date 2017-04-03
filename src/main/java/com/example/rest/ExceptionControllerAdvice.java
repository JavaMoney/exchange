package com.example.rest;

import com.example.model.ExchangeNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

  @ExceptionHandler(ExchangeNotFoundException.class)
  public ResponseEntity<String> notFoundHandler(ExchangeNotFoundException e) {
    return ResponseEntity.notFound().build();
  }

}