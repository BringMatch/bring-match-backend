package com.example.testpfsentities.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Room {
    @Id
    private Long room_id;

    private String name;

    @OneToOne
    private Match match;

    @OneToMany
    private List<Team> teams;
}
