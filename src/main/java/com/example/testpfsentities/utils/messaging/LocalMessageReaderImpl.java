package com.example.testpfsentities.utils.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component

public class LocalMessageReaderImpl implements LocalMessageReader {

    @Autowired
    MessageSource messageSource;

    public String getMessage(String code) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code.trim(), null, locale);
    }

    @Override
    public void setLocale(Locale locale) {
        LocaleContextHolder.setLocale(locale);
    }

}
