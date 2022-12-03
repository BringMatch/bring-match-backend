package com.example.testpfsentities.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = "{ground , notificationOwner}")
public class Reservation extends AbstractEntity {

    @ManyToOne(cascade =CascadeType.ALL)
    private Ground ground;

    @OneToOne(mappedBy = "reservation")
    private NotificationOwner notificationOwner;

    private String team_one_creator_id;
    private String team_two_creator_id;

    private boolean status;

}
