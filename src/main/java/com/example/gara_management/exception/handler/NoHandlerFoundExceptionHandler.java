package com.example.gara_management.exception.handler;

import com.example.gara_management.constant.ErrorCode;
import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.payload.response.base.ErrorResponse;
import com.google.common.collect.ImmutableMap;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.NoHandlerFoundException;

@Log4j2
@Component
public class NoHandlerFoundExceptionHandler implements
        ServletExceptionHandler<NoHandlerFoundException> {

  @Override
  public ResponseEntity<Object> handle(NoHandlerFoundException e, HttpServletRequest request) {

    ErrorResponse errorResponse = ErrorResponse.create()
        .errorCodes(ErrorCode.NO_HANDLER_FOUND)
        .extraData(ImmutableMap.of("request_url", e.getRequestURL()))
        .buildDebugInfo(e);

    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(BaseResponse.of(errorResponse));

  }

}
