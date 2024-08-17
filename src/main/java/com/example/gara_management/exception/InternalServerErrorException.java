package com.example.gara_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends BaseApiException {

  public InternalServerErrorException(String msg) {
    super(msg);
  }

}
