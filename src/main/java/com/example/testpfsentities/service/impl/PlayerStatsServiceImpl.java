package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.PlayerStatsDto;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.mapper.PlayerStatsMapper;
import com.example.testpfsentities.repository.PlayerRepository;
import com.example.testpfsentities.repository.PlayerStatsRepository;
import com.example.testpfsentities.service.PlayerService;
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
//    private final PlayerService playerService;
    private final PlayerStatsMapper playerStatsMapper;
    private final PlayerRepository playerRepository;

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
        return 0;
    }

    @Override
    public void updateGoalsScoredWhenMatchEnds(List<PlayerStatsDto> list) {
        for (PlayerStatsDto playerStatsDto : list) {
            var player = playerRepository.findById(playerStatsDto.getPlayer().getId()).get();
            var playerStat = playerStatsMapper.toBo(playerStatsDto);
            playerStat.setPlayer(player);
            playerStatsRepository.save(playerStat);
        }
    }


}
