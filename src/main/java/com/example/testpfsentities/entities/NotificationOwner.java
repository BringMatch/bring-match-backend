package com.example.testpfsentities.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class NotificationOwner extends Notification {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "owner")
    private List<Reservation> reservations;
}
