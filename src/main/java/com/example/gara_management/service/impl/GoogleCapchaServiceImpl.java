package com.example.gara_management.service.impl;

import com.example.gara_management.payload.response.GoogleRecaptchaResponse;
import com.example.gara_management.proxy.GoogleCaptchaProxy;
import com.example.gara_management.reCapcha.ReCaptchaKey;
import com.example.gara_management.service.GoogleCapchaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class GoogleCapchaServiceImpl implements GoogleCapchaService {

  private final ReCaptchaKey reCaptchaKey;

  private final GoogleCaptchaProxy googleCaptchaProxy;

  @Override
  public GoogleRecaptchaResponse verifyGoogleCapcha(String response) {

    GoogleRecaptchaResponse googleRecaptchaResponse = googleCaptchaProxy.siteVerify(
        reCaptchaKey.getSecret(), response).getBody();
    log.info("-------response after verify:");
    if (googleRecaptchaResponse != null) {
      if (googleRecaptchaResponse.isSuccess()
          && googleRecaptchaResponse.getScore() < reCaptchaKey.getThreshold()) {
        googleRecaptchaResponse.setSuccess(false);
      }
    }
    return googleRecaptchaResponse;
  }

}
