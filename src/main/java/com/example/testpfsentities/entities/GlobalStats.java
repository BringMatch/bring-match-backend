package com.example.testpfsentities.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
public class GlobalStats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long numGoalsTeamOne;
    private Long numGoalsTeamTwo;

    private String finalScore;

    private Long totalMinPlayed;

    private LocalDateTime date;

//    @OneToOne
//    private Player MOTM;


}
