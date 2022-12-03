package com.example.testpfsentities.entities;

import com.example.testpfsentities.entities.enums.MatchType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(value = {"ground"}, allowSetters = true)
public class Match extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private MatchType matchType;
    @OneToOne
    private GlobalStats globalStats;
    @ManyToOne
    private Ground ground;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Team> teams;

    private Boolean privateMatch;


    private String matchCode;

}
