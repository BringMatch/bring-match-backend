package com.example.testpfsentities.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Reservation extends AbstractEntity {
    @OneToOne
    private Match match;

    @ManyToOne
    private Ground ground;

    @OneToOne(mappedBy = "reservation")
    private NotificationOwner notificationOwner;

    private String team_one_creator_id;
    private String team_two_creator_id;

    private boolean status;

}
