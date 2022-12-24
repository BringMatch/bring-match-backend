package com.example.testpfsentities.dto;

import com.example.testpfsentities.entities.PlayerStats;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String email;
    private String roleName;
    private String town;
    private String region;
    private String username;
    private List<PlayerStatsDto> playerStats;
}
