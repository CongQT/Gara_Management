package com.example.gara_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends BaseApiException {

  public ForbiddenException(String msg) {
    super(msg);
  }

}
