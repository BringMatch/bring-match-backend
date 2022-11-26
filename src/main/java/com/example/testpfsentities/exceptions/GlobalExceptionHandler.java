package com.example.testpfsentities.exceptions;

import com.example.testpfsentities.utils.messaging.LocalMessageReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public static final String BUSINESS_EXCEPTION_HAS_OCCURED = "Business exception {} has occured";
    public static final String TECHNICAL_EXCEPTION_HAS_OCCURED = "Technical exception {} has occured";

    @Autowired
    private LocalMessageReader localMessageReader;

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException e) {
        log.warn(BUSINESS_EXCEPTION_HAS_OCCURED, e.getMessage());
        List<Message> messages = e.getMessageList();
        messages.forEach(message -> Optional.ofNullable(message.getCode()).ifPresent(code -> message.setText(localMessageReader.getMessage(code))));
        return new ResponseEntity<>(messages, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<Object> handleTechnicalException(TechnicalException e) {
        log.error(TECHNICAL_EXCEPTION_HAS_OCCURED, e.getMessage());
        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}