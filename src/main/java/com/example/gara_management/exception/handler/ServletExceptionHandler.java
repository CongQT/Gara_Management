package com.example.gara_management.exception.handler;

import com.example.gara_management.util.Generic;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface ServletExceptionHandler<E extends Exception> extends Generic<E> {

  default Class<E> getExceptionClass() {
    return getClassParam();
  }

  ResponseEntity<Object> handle(E e, HttpServletRequest request);

  default ResponseEntity<Object> handleException(Exception e, HttpServletRequest request) {
    return handle(getExceptionClass().cast(e), request);
  }

}
