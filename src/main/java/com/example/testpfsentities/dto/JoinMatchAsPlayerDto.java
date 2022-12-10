package com.example.testpfsentities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinMatchAsPlayerDto {
    private String id;
    private String player_id;
    private String team_id;
}
