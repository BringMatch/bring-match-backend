package com.example.testpfsentities.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Owner extends User{


    @OneToMany(mappedBy = "owner")
    private List<Ground> grounds;

    @OneToMany(mappedBy = "owner")
    private List<NotificationOwner> notificationPlayer;

    private Boolean active;
    private String num_cin;

    private String num_certif_prop;

}
