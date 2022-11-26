package com.example.testpfsentities.utils.messaging;

import java.util.Locale;

public interface LocalMessageReader {
    String getMessage(String code);
    void setLocale(Locale locale);
}
