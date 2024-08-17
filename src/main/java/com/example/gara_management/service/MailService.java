package com.example.gara_management.service;

import com.example.gara_management.mail.message.MailMessage;
import jakarta.mail.internet.MimeMessage;

public interface MailService {

    MimeMessage buildMimeMessage(MailMessage message);

    void send(MimeMessage message);

    default void send(MailMessage message) {
        send(buildMimeMessage(message));
    }


}