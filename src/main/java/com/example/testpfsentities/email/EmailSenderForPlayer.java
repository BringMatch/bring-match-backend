package com.example.testpfsentities.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSenderForPlayer {
    private final JavaMailSender javaMailSender;

    public EmailSenderForPlayer(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("BringMatch2022@gmail.com");
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(body);

        javaMailSender.send(msg);
        log.info("mail send successfully");
    }
}
