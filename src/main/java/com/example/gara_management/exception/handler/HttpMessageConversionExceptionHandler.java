package com.example.gara_management.exception.handler;

import com.example.gara_management.constant.ErrorCode;
import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.payload.response.base.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class HttpMessageConversionExceptionHandler implements
        ServletExceptionHandler<HttpMessageConversionException> {

  @Override
  public ResponseEntity<Object> handle(HttpMessageConversionException e,
      HttpServletRequest request) {

    ErrorResponse errorResponse = ErrorResponse.create()
        .errorCodes(ErrorCode.INVALID_INPUT_DATA)
        .buildDebugInfo(e);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(BaseResponse.of(errorResponse));

  }

}
