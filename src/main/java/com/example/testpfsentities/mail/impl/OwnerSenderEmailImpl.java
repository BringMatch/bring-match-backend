package com.example.testpfsentities.mail.impl;

import com.example.testpfsentities.dto.EmailDto;
import com.example.testpfsentities.entities.Owner;
import com.example.testpfsentities.mail.EmailSender;
import com.example.testpfsentities.mail.OwnerSenderEmail;
import org.springframework.stereotype.Component;

@Component
public class OwnerSenderEmailImpl extends AbstractEmailSender implements OwnerSenderEmail {
    public OwnerSenderEmailImpl(EmailSender emailSender) {
        super(emailSender);
    }

    @Override
    public void newEmailSender(Owner owner) {
        String subject = "compte créé avec succes";
        String body = "Bienvenue chez Bring Match " + System.lineSeparator() +
                "Merci pour votre inscription à notre application " + System.lineSeparator() +
                "votre inscription est acceptée" + System.lineSeparator() +
                "Merci de ne pas répondre a cette email";
        EmailDto emailDto = EmailDto.builder()
                .to(owner.getEmail())
                .from(emailFrom)
                .hasHtml(true)
                .subject(subject)
                .content(body)
                .build();
        emailSender.send(emailDto);
    }
}
