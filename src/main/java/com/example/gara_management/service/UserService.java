package com.example.gara_management.service;


import com.example.gara_management.entity.User;
import com.example.gara_management.payload.request.ChangePasswordRequest;
import com.example.gara_management.payload.request.RegistrationRequest;
import com.example.gara_management.payload.request.UserInfoRequest;
import com.example.gara_management.payload.response.UserInfoResponse;

public interface UserService extends BaseEntityService<User, Integer> {

  User findByUsername(String username);

  void createUserAccount(RegistrationRequest request);

  void changePassword(ChangePasswordRequest request);

  UserInfoResponse info();

  UserInfoResponse updateInfo(UserInfoRequest request);

  void delete(Integer id);


}