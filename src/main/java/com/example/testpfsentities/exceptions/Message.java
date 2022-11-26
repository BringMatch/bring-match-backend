package com.example.testpfsentities.exceptions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Message implements Serializable {
    String code;
    String text;
    String fieldName;
    Level level;

    public Message() {
        super();
        this.level = Level.ERROR;

    }

    public Message( String code) {
        super();
        this.code = code;
        this.level = Level.ERROR;
    }
    public Message( String code,String message, String fieldName) {
        super();
        this.code = code;
        this.text = message;
        this.fieldName = fieldName;
        this.level = Level.ERROR;
    }

    public Message( String code,String message, String fieldName, Level level) {
        super();
        this.code = code;
        this.text = message;
        this.fieldName = fieldName;
        this.level = level;
    }

    public Message( String code, String fieldName, Level level) {
        super();
        this.code = code;
        this.fieldName = fieldName;
        this.level = level;
    }
}
