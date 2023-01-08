package com.example.testpfsentities.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"playersTeams", "playerStat", "notificationPlayer"}, allowSetters = true)
public class Player extends User {
    //    @ManyToMany(mappedBy = "")
//    private List<Team> teams;
//    @OneToMany(mappedBy = "player", orphanRemoval = true)
//    List<TeamPlayer> playersTeams;

    @OneToOne(mappedBy = "player", orphanRemoval = true)
    private PlayerStats playerStat;

    @OneToMany(mappedBy = "player")
    private List<NotificationPlayer> notificationPlayer;

    private String town;
    private String region;

}
