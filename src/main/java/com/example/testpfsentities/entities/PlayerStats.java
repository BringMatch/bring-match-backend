package com.example.testpfsentities.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter

public class PlayerStats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Long numGoals;
    private Long numAssists;


    private boolean MOTM;

    @ManyToOne
    private Player player;

}
