package com.example.gara_management.exception.handler;

import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.payload.response.base.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AccessDeniedExceptionHandler implements
        ServletExceptionHandler<AccessDeniedException> {

  @Override
  public ResponseEntity<Object> handle(AccessDeniedException e, HttpServletRequest request) {

    ErrorResponse errorResponse = ErrorResponse.create()
        .errorCodes("ACCESS_DENIED")
        .buildDebugInfo(e);

    return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(BaseResponse.of(errorResponse));

  }


}
