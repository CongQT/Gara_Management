package com.example.gara_management.exception.handler;

import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.payload.response.base.ErrorResponse;
import com.google.common.collect.ImmutableMap;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Component
public class MethodArgumentNotValidExceptionHandler implements
        ServletExceptionHandler<MethodArgumentNotValidException> {

  @Override
  public ResponseEntity<Object> handle(MethodArgumentNotValidException e,
      HttpServletRequest request) {

    List<String> errorCodes = e.getAllErrors().stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .distinct()
        .collect(Collectors.toList());

    List<ImmutableMap<String, Object>> fieldErrors = e.getFieldErrors()
        .stream()
        .map(fieldError -> ImmutableMap.of(
            "message", StringUtils.defaultString(fieldError.getDefaultMessage(), ""),
            "field", fieldError.getField(),
            "rejected_value", ObjectUtils.defaultIfNull(fieldError.getRejectedValue(), "")))
        .collect(Collectors.toList());

    ErrorResponse errorResponse = ErrorResponse.create()
        .errorCodes(errorCodes)
        .extraData(fieldErrors);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(BaseResponse.of(errorResponse));

  }

}
