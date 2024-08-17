package com.example.gara_management.security.core.userdetails.impl;

import com.example.gara_management.entity.User;
import com.example.gara_management.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) {

    User user = userService.findByUsername(username);
    return UserDetailsImpl.builder()
            .user(user)
            .build();

  }

}