package com.example.gara_management.service;

import com.example.gara_management.entity.User;
import com.example.gara_management.payload.request.LoginRequest;
import com.example.gara_management.payload.response.LoginResponse;

public interface LoginService {

    LoginResponse login(LoginRequest request);


    LoginResponse login(User user);
}
