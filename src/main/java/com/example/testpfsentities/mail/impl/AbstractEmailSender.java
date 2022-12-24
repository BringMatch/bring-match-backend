package com.example.testpfsentities.mail.impl;

import com.example.testpfsentities.mail.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@RequiredArgsConstructor
public abstract class AbstractEmailSender {

    protected final EmailSender emailSender;
    @Value("${mail.address.from}")
    protected String emailFrom;
}
