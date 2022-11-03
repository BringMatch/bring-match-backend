package com.example.testpfsentities.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class NotificationAdmin extends Notification {

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

}
