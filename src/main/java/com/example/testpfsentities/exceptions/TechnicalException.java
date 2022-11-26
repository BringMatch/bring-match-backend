package com.example.testpfsentities.exceptions;



public class TechnicalException  extends  RuntimeException {

    public TechnicalException(Throwable e) {
        super(e);
    }

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException() {
        super();
    }
}
