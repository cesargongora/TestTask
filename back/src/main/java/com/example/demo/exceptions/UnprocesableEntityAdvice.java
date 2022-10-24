package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class UnprocesableEntityAdvice {

  @ResponseBody
  @ExceptionHandler(UnprocesableEntity.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  String employeeNotFoundHandler(UnprocesableEntity ex) {
    return ex.getMessage();
  }
}