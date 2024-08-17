package com.example.gara_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseApiException {

  public BadRequestException(String msg) {
    super(msg);
  }

}
