package com.example.gara_management.mail.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MailMessage {

    private String senderName;
    private String senderAddress;
    private String subject;
    private String content;
    private boolean isHtml;
    private Collection<MailAttachment> mailAttachments;
    private Collection<String> receivers;

}
