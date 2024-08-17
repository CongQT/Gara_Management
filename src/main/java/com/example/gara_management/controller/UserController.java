package com.example.gara_management.controller;

import com.example.gara_management.constant.RoleCode;
import com.example.gara_management.payload.request.ChangePasswordRequest;
import com.example.gara_management.payload.request.LoginRequest;
import com.example.gara_management.payload.request.RegistrationRequest;
import com.example.gara_management.payload.request.UserInfoRequest;
import com.example.gara_management.payload.response.UserInfoResponse;
import com.example.gara_management.payload.response.base.BaseResponse;
import com.example.gara_management.service.LoginService;
import com.example.gara_management.service.SignupService;
import com.example.gara_management.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Log4j2
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/change_password")
    public BaseResponse<Object> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        userService.changePassword(request);
        return BaseResponse.of(Collections.emptyMap());
    }

    @GetMapping("/info")
    public BaseResponse<UserInfoResponse> getUserInfo() {
        return BaseResponse.of(userService.info());
    }

    @RolesAllowed(RoleCode.MANAGER)
    @PostMapping("/create_staff_account")
    public BaseResponse<Object> createAccount(@RequestBody @Valid RegistrationRequest request) {
        userService.createUserAccount(request);
        return BaseResponse.of(Collections.emptyMap());
    }

    @PostMapping("/update_info")
    public BaseResponse<UserInfoResponse> updateInfo(@RequestBody @Valid UserInfoRequest request) {
        return BaseResponse.of(userService.updateInfo(request));
    }

    @RolesAllowed(RoleCode.MANAGER)
    @DeleteMapping("/detele")
    public BaseResponse<Object> delete(@RequestParam(name = "id") Integer id) {
        userService.delete(id);
        return BaseResponse.of(Collections.emptyMap());
    }
}
