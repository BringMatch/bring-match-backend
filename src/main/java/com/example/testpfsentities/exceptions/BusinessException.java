package com.example.testpfsentities.exceptions;

import com.google.gson.Gson;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final List<Message> messages;

    public BusinessException() {
        super();
        messages = new ArrayList<>();
    }
    public BusinessException(String code) {
        super();
        messages = new ArrayList<>();
        messages.add(new Message(code));
    }
    public BusinessException(Message message) {
        super();
        messages = new ArrayList<>();
        messages.add(message);
    }
    public BusinessException(List<Message> messages) {
        super();
        this.messages = messages;
    }

    public List<Message> getMessageList() {
        return messages;
    }

    @Override
    public String getMessage() {
        String message = null;
        if (CollectionUtils.isNotEmpty(messages)) {
            Gson gson = new Gson();
            message =  gson.toJson(messages);
        }
        return message;
    }


}