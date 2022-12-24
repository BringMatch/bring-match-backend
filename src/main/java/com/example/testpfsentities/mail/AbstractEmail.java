package com.example.testpfsentities.mail;

public interface AbstractEmail<E> {
    void send(E e);
}
