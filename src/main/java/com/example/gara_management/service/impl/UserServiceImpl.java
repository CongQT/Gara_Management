package com.example.gara_management.service.impl;

import com.example.gara_management.constant.ErrorCode;
import com.example.gara_management.constant.RoleCode;
import com.example.gara_management.entity.*;
import com.example.gara_management.exception.BadRequestException;
import com.example.gara_management.exception.NotFoundException;
import com.example.gara_management.payload.request.ChangePasswordRequest;
import com.example.gara_management.payload.request.RegistrationRequest;
import com.example.gara_management.payload.request.UserInfoRequest;
import com.example.gara_management.payload.response.UserInfoResponse;
import com.example.gara_management.repository.*;
import com.example.gara_management.security.core.userdetails.impl.UserDetailsImpl;
import com.example.gara_management.service.UserService;
import com.example.gara_management.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public JpaRepository<User, Integer> getRepository() {
        return userRepo;
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findFirstByUsername(username)
                .orElseThrow(() -> new NotFoundException("Not found user with username: " + username)
                        .errorCode(ErrorCode.USER_NOT_FOUND)
                        .extraData("username", username));
    }

    @Override
    public void createUserAccount(RegistrationRequest request) {

        userRepo.findFirstByUsername(request.getUsername())
                .ifPresent(user -> {
                    throw new BadRequestException("Existed user with username: " + request.getUsername())
                            .errorCode(ErrorCode.USERNAME_EXISTED)
                            .extraData("username", request.getUsername());
                });

        Role role = roleRepo.findById(request.getRoleId()).orElseThrow(() -> new NotFoundException("Not found role id with id: " + request.getRoleId())
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

    @Override
    public void changePassword(ChangePasswordRequest request) {
        User user = SecurityUtils.getUserDetails(UserDetailsImpl.class).getUser();
        if (Objects.equals(request.getPassword(), request.getOldPassword())) {
            throw new BadRequestException("Password is not changed")
                    .errorCode(ErrorCode.PASSWORD_NOT_CHANGED);
        } else if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("Old password is incorrect")
                    .errorCode(ErrorCode.OLD_PASSWORD_INCORRECT);
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepo.save(user);
    }

    @Override
    public UserInfoResponse info() {
        User user = SecurityUtils.getUserDetails(UserDetailsImpl.class).getUser();
        return modelMapper.map(user, UserInfoResponse.class);
    }

    @Override
    public UserInfoResponse updateInfo(UserInfoRequest request) {
        User user = SecurityUtils.getUserDetails(UserDetailsImpl.class).getUser();
        modelMapper.map(request, user);
        userRepo.save(user);
        return modelMapper.map(user, UserInfoResponse.class);
    }

    @Override
    public void delete(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new NotFoundException("Not found user with id: " + id)
                .errorCode(ErrorCode.USER_NOT_FOUND)
                .extraData("user_id", id));
        userRepo.delete(user);
    }

}
