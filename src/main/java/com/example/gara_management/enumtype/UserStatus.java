package com.example.gara_management.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserStatus {

  NOT_VERIFIED("Chưa xác thực"),
  ACTIVE("Active"),
  INACTIVE("Inactive"),
  REJECTED("Từ chối"),
  DELETED("Đã xóa"),
  ;

  private final String name;

}
