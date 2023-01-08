package com.example.testpfsentities.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(allowSetters = true, value = {"player"})
public class PlayerStats extends AbstractEntity {

    private int numGoals;
    @OneToOne(orphanRemoval = true,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Player player;

    private String match_id;

}
