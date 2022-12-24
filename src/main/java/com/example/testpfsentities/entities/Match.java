package com.example.testpfsentities.entities;

import com.example.testpfsentities.entities.enums.MatchStatus;
import com.example.testpfsentities.entities.enums.MatchType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"ground"})
public class Match extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private MatchType matchType;
    @ManyToOne
    private Ground ground;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Team> teams;

    private Boolean privateMatch;
    private String matchCode;
    private Date date;
    private String town;
    private String region;
    private int startHour;
    private int duration;
    private int numberTeamPlayers;

    @Enumerated(EnumType.STRING)
    private MatchStatus matchStatus;

    private boolean draw;

}
