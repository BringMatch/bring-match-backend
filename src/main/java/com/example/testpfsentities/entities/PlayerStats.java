package com.example.testpfsentities.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(allowSetters = true, value = {"player"})
public class PlayerStats extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private String id;

    private int numGoals;
    private boolean MOTM;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Player player;

}
