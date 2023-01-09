package com.example.testpfsentities.controller.common;

import com.example.testpfsentities.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    public static final String BUSINESS_EXCEPTION_HAS_OCCURED = "Business exception {} has occured";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handlerException(IllegalArgumentException illegalArgumentException) {
        String message = illegalArgumentException.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handlerException(HttpMessageNotReadableException httpMessageNotReadableException) {
        String message = httpMessageNotReadableException.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handlerException(MethodArgumentNotValidException methodArgumentNotValidException) {
        String message = methodArgumentNotValidException.getFieldError().getDefaultMessage();
        String field = methodArgumentNotValidException.getFieldError().getField();
        Map<String, String> map = new HashMap<>();
        map.put(field, message);
        return ResponseEntity.status(400).body(map);
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException e) {
        log.warn(BUSINESS_EXCEPTION_HAS_OCCURED, e.getMessage());
        return ResponseEntity.status(400).body(new ResponseError(Collections.singletonList(e.getMessage()), 400));
    }

}
