package com.example.gara_management.enumtype;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MailTemplate {

    USER_VERIFICATION("[GetIns] Xác thực khởi tạo tài khoản", "01-user-verification"),
    ;

    private final String subject;
    private final String template;

}
