package com.example.gara_management.exception.handler;

import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.payload.response.base.ErrorResponse;
import com.google.common.collect.ImmutableMap;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Component
public class ConstraintViolationExceptionHandler implements
        ServletExceptionHandler<ConstraintViolationException> {

  @Override
  public ResponseEntity<Object> handle(ConstraintViolationException e, HttpServletRequest request) {

    Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
    List<String> errorCodes = constraintViolations.stream().map(ConstraintViolation::getMessage)
        .collect(Collectors.toList());
    List<Map<String, Object>> violationData = constraintViolations.stream()
        .map(constraintViolation -> ImmutableMap.of(
            "message", constraintViolation.getMessage(),
            "property_path", constraintViolation.getPropertyPath().toString(),
            "invalid_value", ObjectUtils.defaultIfNull(constraintViolation.getInvalidValue(), "")))
        .collect(Collectors.toList());

    ErrorResponse errorResponse = ErrorResponse.create()
        .errorCodes(errorCodes)
        .extraData(violationData);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(BaseResponse.of(errorResponse));

  }

}
