package com.example.gara_management.service.impl;

import com.example.gara_management.exception.BaseApiException;
import com.example.gara_management.mail.message.MailMessage;
import com.example.gara_management.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.stream.Streams;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Log4j2
@Service
@RequiredArgsConstructor
public class SmtpMailServiceImpl implements MailService {

  private final JavaMailSender javaMailSender;

  @Override
  public MimeMessage buildMimeMessage(MailMessage message) {

    try {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      new MimeMessageHelper(mimeMessage, UTF_8.name()) {{
        setFrom(message.getSenderAddress(), message.getSenderName());
        setTo(message.getReceivers().toArray(new String[0]));
        setSubject(message.getSubject());
        setText(message.getContent(), message.isHtml());
        if (CollectionUtils.isNotEmpty(message.getMailAttachments())) {
          Streams.stream(message.getMailAttachments())
              .forEach(mailAttachment -> addAttachment(
                  mailAttachment.getName(),
                  new ByteArrayResource(mailAttachment.getData()))
              );
        }
      }};
      return mimeMessage;
    } catch (MessagingException | UnsupportedEncodingException e) {
      throw new BaseApiException(e);
    }
  }

  public void send(MimeMessage message) {
    javaMailSender.send(message);
  }

}

