package com.example.gara_management.controller;

import com.example.gara_management.payload.request.LoginRequest;
import com.example.gara_management.payload.request.RegistrationRequest;
import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.service.LoginService;
import com.example.gara_management.service.SignupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Log4j2
@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class AccountController {

    private final SignupService signupService;
    private final LoginService loginService;

    @PostMapping("/registration")
    public BaseResponse<Object> signup(@RequestBody @Valid RegistrationRequest request,
                                       @RequestParam(name = "response") String response) {
        signupService.signup(response, request);
        return BaseResponse.of(Collections.emptyMap());
    }

    @PostMapping("/login")
    public BaseResponse<Object> login(@RequestBody @Valid LoginRequest request) {
        return BaseResponse.of(loginService.login(request));
    }
}
