package com.example.gara_management.service;

import com.example.gara_management.payload.request.RegistrationRequest;

public interface SignupService {

  void signup(String response, RegistrationRequest request);

}