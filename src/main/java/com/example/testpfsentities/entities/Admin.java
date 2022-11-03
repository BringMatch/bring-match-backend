package com.example.testpfsentities.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Admin extends User{
    @OneToMany
    private List<Owner> owners;

    @OneToMany(mappedBy = "admin")
    private List<NotificationAdmin> notificationAdmins;
}
