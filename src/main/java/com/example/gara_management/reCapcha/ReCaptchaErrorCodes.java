package com.example.gara_management.reCapcha;

import java.util.Map;

public class ReCaptchaErrorCodes {
  public static final Map<String,String> RECAPTCHA_ERROR_CODES=Map.of(
      "missing-input-secret","The secret parameter is missing",
      "invalid-input-secret","The secret parameter is invalid or malformed",
      "missing-input-response","The response parameter is missing",
      "invalid-input-response","The response parameter is invalid or malformed",
      "bad-request","The request is invalid or malformed",
      "timeout or duplicate", "The response is no longer valid:either is too old or has been used previously"
  );

}
