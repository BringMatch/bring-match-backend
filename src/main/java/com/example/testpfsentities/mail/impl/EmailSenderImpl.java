package com.example.testpfsentities.mail.impl;

import com.example.testpfsentities.dto.EmailDto;
import com.example.testpfsentities.exceptions.TechnicalException;
import com.example.testpfsentities.mail.EmailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSender emailSender;

    @Override
    public void send(EmailDto emailDto) {
        log.info("Sending email to {}", emailDto.getTo());
        try {
            MimeMessage message = emailSender.createMimeMessage();
            message.setSubject(emailDto.getSubject());
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, "UTF-8");
            helper.setFrom(emailDto.getFrom());
            helper.setTo(emailDto.getTo());
            helper.setText(emailDto.getContent(), emailDto.isHasHtml());
            emailSender.send(message);
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }
}
