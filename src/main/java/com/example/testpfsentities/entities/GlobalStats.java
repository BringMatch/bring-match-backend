package com.example.testpfsentities.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class GlobalStats extends AbstractEntity {
    private Long numGoalsTeamOne;
    private Long numGoalsTeamTwo;
    private String finalScore;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Match match;

}
