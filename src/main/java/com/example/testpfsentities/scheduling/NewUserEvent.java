package com.example.testpfsentities.scheduling;

import com.example.testpfsentities.entities.Owner;
import com.example.testpfsentities.entities.User;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class NewUserEvent extends ApplicationEvent {
    private Owner owner;
    public NewUserEvent(Object source , Owner owner) {
        super(source);
        this.owner = owner;
    }

    public Owner getUser() {
        return owner;
    }
}
