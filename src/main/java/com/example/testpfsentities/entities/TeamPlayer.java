package com.example.testpfsentities.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "players_teams")
@JsonIgnoreProperties(value = {"team"},allowSetters = true)
public class TeamPlayer extends AbstractEntity {


    @ManyToOne
    @JoinColumn(name = "player_id")
    Player player;

    @ManyToOne
    @JoinColumn(name = "team_id")
    Team team;

    @Column(columnDefinition = "boolean default false")
    private boolean match_owner;

    @Column(columnDefinition = "boolean default false")
    private boolean team_owner;

    private String position;

}
