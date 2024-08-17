package com.example.gara_management.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AppointmentStatus {

  NOT_VERIFIED("Chưa xác thực"),
  APPROVED("Chấp nhận"),
  CANCEL("Đã huỷ"),
  COMPLETE("Hoàn thành"),
  ;

  private final String name;

}
