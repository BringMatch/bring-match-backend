package com.example.testpfsentities.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class NotificationOwner extends Notification {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
