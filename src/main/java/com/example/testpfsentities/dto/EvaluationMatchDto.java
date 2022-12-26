package com.example.testpfsentities.dto;

import com.example.testpfsentities.entities.Match;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationMatchDto {
    private GlobalStatsDto globalStatsDto;
    private List<PlayerStatsDto> players;
}
