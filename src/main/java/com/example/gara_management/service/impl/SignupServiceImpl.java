package com.example.gara_management.service.impl;

import com.example.gara_management.constant.ErrorCode;
import com.example.gara_management.entity.Role;
import com.example.gara_management.entity.User;
import com.example.gara_management.exception.BadRequestException;
import com.example.gara_management.exception.NotFoundException;
import com.example.gara_management.payload.request.RegistrationRequest;
import com.example.gara_management.payload.response.GoogleRecaptchaResponse;
import com.example.gara_management.repository.RoleRepository;
import com.example.gara_management.repository.UserRepository;
import com.example.gara_management.service.GoogleCapchaService;
import com.example.gara_management.service.SignupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    private final GoogleCapchaService googleCapchaService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(String response, RegistrationRequest request) {
        GoogleRecaptchaResponse googleRecaptchaResponse = googleCapchaService.verifyGoogleCapcha(
                response);
        if (!googleRecaptchaResponse.isSuccess()) {
            throw new BadRequestException("INVALID_CAPTCHA").errorCode(
                            String.join(",", googleRecaptchaResponse.getErrorCodes()))
                    .extraData("google reCaptcha response", googleRecaptchaResponse);
        }
        userRepo.findFirstByUsername(request.getEmail())
                .ifPresent(user -> {
                    throw new BadRequestException("Existed user with username: " + request.getUsername())
                            .errorCode(ErrorCode.USERNAME_EXISTED)
                            .extraData("username", request.getUsername());
                });

        Role role = roleRepo.findById(5).orElseThrow(() -> new NotFoundException("Not found role id with id: " + request.getRoleId())
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("role_id", request.getRoleId()));

        User user = User.builder()
                .username(request.getUsername())
                .role(role)
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .build();
        userRepo.save(user);

    }
}