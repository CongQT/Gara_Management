package com.example.gara_management.exception.handler;

import com.example.gara_management.constant.ErrorCode;
import com.example.gara_management.exception.BaseApiException;
import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.payload.response.base.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AuthenticationExceptionHandler implements
        ServletExceptionHandler<AuthenticationException> {

  @Override
  public ResponseEntity<Object> handle(AuthenticationException e, HttpServletRequest request) {

    ErrorResponse errorResponse = ErrorResponse.create()
        .buildDebugInfo(e);

    Throwable cause = e.getCause();
    if (cause instanceof BaseApiException baseApiException) {
      errorResponse.errorCodes(baseApiException.errorCodes())
          .extraData(baseApiException.extraData());
    } else if (e instanceof BadCredentialsException) {
      errorResponse.errorCodes(ErrorCode.BAD_CREDENTIALS);
    } else {
      errorResponse.errorCodes(ErrorCode.UNAUTHORIZED);
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        .body(BaseResponse.of(errorResponse));

  }


}
