package com.example.testpfsentities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalStatsDto {
    private Long id;
    private Long numGoalsTeamOne;
    private Long numGoalsTeamTwo;
    private String finalScore;
    private Long totalMinPlayed;
    private LocalDateTime date;
    private String match_id;
}
