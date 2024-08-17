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
import org.springframework.web.bind.MissingServletRequestParameterException;

@Log4j2
@Component
public class MissingServletRequestParameterExceptionHandler
    implements ServletExceptionHandler<MissingServletRequestParameterException> {

  @Override
  public ResponseEntity<Object> handle(MissingServletRequestParameterException e,
      HttpServletRequest request) {

    ErrorResponse errorResponse = ErrorResponse.create()
        .errorCodes(ErrorCode.MISSING_PARAMETER)
        .extraData(ImmutableMap.of(
            "parameter_name", e.getParameterName(),
            "parameter_type", e.getParameterType()
        ));

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(BaseResponse.of(errorResponse));

  }

}
