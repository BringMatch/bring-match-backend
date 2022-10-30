package com.example.testpfsentities.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Player extends User {
    @ManyToMany
    @JoinTable(name = "players_teams",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams;


    @Embedded
//    @OneToOne
    private Evaluation evaluation;

    @OneToMany(mappedBy = "playerStats")
    private List<Statistic> statisticsPlayers;

    @OneToMany
    private List<NotificationPlayer> notificationPlayer;

}
