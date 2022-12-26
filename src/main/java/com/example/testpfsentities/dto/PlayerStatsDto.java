package com.example.testpfsentities.dto;

import com.example.testpfsentities.entities.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerStatsDto {

    private int numGoals;
    private boolean MOTM;
    private PlayerDto player;
}
