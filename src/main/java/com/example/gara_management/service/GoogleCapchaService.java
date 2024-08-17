package com.example.gara_management.service;

import com.example.gara_management.payload.response.GoogleRecaptchaResponse;

public interface GoogleCapchaService {

  GoogleRecaptchaResponse verifyGoogleCapcha(String response);

}