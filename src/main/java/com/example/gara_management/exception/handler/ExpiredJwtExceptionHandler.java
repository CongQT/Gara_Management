package com.example.gara_management.exception.handler;

import com.example.gara_management.constant.ErrorCode;
import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.payload.response.base.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ExpiredJwtExceptionHandler implements ServletExceptionHandler<ExpiredJwtException> {

  @Override
  public ResponseEntity<Object> handle(ExpiredJwtException e, HttpServletRequest request) {

    ErrorResponse errorResponse = ErrorResponse.create()
        .errorCodes(ErrorCode.EXPIRED_TOKEN)
        .buildDebugInfo(e);

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(BaseResponse.of(errorResponse));

  }


}
