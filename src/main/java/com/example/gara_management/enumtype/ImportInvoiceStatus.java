package com.example.gara_management.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ImportInvoiceStatus {

  NOT_PAID("Chưa thanh toán"),
  PAID("Đã thanh toán")
  ;

  private final String name;

}
