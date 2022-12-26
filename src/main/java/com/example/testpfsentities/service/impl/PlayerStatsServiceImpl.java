package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.PlayerStatsDto;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.repository.PlayerStatsRepository;
import com.example.testpfsentities.service.PlayerStatsService;
import com.example.testpfsentities.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlayerStatsServiceImpl implements PlayerStatsService {
    private final PlayerStatsRepository playerStatsRepository;
    private final UserService userService;

    @Override
    public void savePlayerStats() {
        var player = userService.getPlayerConnected();
        PlayerStats playerStats = new PlayerStats();
        playerStats.setNumGoals(0);
        playerStats.setPlayer(player);
        playerStatsRepository.save(playerStats);
    }

    @Override
    public Integer getGoalsScoredByPlayer(Player player) {
        var list = playerStatsRepository.findAll();
        for (PlayerStats playerStats : list) {
            if (playerStats.getPlayer().getId().equals(player.getId())) {
                return playerStats.getNumGoals();
            }
        }
        return null;
    }

    @Override
    public void updateGoalsScoredWhenMatchEnds(Match match , List<PlayerStatsDto> list) {

    }


}
