package com.example.demo.exceptions;

public class UnprocesableEntity extends RuntimeException {
  
    public UnprocesableEntity(String msg) {
      super(msg);
    }
  }
