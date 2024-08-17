package com.example.gara_management.proxy;

import com.example.gara_management.payload.response.GoogleRecaptchaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "reCaptcha",
        url = "https://www.google.com/recaptcha/api/siteverify"
)
public interface GoogleCaptchaProxy {

    @PostMapping
    ResponseEntity<GoogleRecaptchaResponse> siteVerify(@RequestParam(name = "secret") String secret,
                                                       @RequestParam(name = "response") String response);

}
