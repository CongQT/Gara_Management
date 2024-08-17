package com.example.gara_management.mail.listener;

import java.util.Collection;
import java.util.Map;

public interface MailListener {

    void onSendTemplateMail(
            String subject,
            String template,
            Map<String, Object> variables,
            Collection<String> receivers,
            Exception e
    );

}
