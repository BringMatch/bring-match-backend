package com.example.testpfsentities.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Player extends User {
//    @ManyToMany(mappedBy = "")
//    private List<Team> teams;

    @OneToMany(mappedBy = "player")
    private List<PlayerStats> playerStats;

    @Column(columnDefinition = "boolean default false")
    private boolean match_owner;

    @Column(columnDefinition = "boolean default false")
    private boolean team_owner;

    @OneToMany(mappedBy = "player")
    private List<NotificationPlayer> notificationPlayer;

}
