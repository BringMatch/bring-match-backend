package com.example.testpfsentities.exceptions;

import com.google.gson.Gson;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final Message message;

    public BusinessException() {
        super();
        message = new Message();
    }

    public BusinessException(Message message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message.getCode();
    }


}