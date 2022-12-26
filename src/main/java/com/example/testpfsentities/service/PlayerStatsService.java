package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.PlayerStatsDto;
import com.example.testpfsentities.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerStatsService {
    void savePlayerStats();

    Integer getGoalsScoredByPlayer(Player player);

    void updateGoalsScoredWhenMatchEnds(Match match , List<PlayerStatsDto> list);

}
