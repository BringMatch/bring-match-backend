package com.example.testpfsentities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GlobalStatsDto {
    private String id;
    private Long numGoalsTeamOne;
    private Long numGoalsTeamTwo;
    private String finalScore;
    private Date createdAt;
    private MatchDto match;
}
