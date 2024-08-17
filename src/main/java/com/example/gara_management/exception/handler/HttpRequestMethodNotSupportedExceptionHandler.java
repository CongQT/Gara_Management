package com.example.gara_management.exception.handler;

import com.example.gara_management.constant.ErrorCode;
import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.payload.response.base.ErrorResponse;
import com.google.common.collect.ImmutableMap;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

@Log4j2
@Component
public class HttpRequestMethodNotSupportedExceptionHandler
    implements ServletExceptionHandler<HttpRequestMethodNotSupportedException> {

  @Override
  public ResponseEntity<Object> handle(HttpRequestMethodNotSupportedException e,
      HttpServletRequest request) {

    ErrorResponse errorResponse = ErrorResponse.create()
        .errorCodes(ErrorCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED)
        .extraData(ImmutableMap.of(
            "method", e.getMethod(),
            "supported_methods",
            ObjectUtils.defaultIfNull(e.getSupportedMethods(), new String[]{})))
        .buildDebugInfo(e);

    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        .body(BaseResponse.of(errorResponse));

  }

}
