package com.example.testpfsentities.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Player extends User {
    @ManyToMany
    @JoinTable(name = "players_teams",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams;


    @OneToMany(mappedBy = "player")
    private List<PlayerStats> playerStats;

    private boolean match_owner;

    @OneToMany(mappedBy = "player")
    private List<NotificationPlayer> notificationPlayer;

}
