package com.example.testpfsentities.entities;

import com.example.testpfsentities.entities.enums.MatchType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter

public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Enumerated(EnumType.STRING)
    private MatchType matchType;


    @OneToOne
    private GlobalStats globalStats;

    @ManyToOne
    private Ground ground;

    @OneToOne(mappedBy = "match")
    private Reservation reservation;

}
