package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.PlayerStatsDto;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.mapper.PlayerStatsMapper;
import com.example.testpfsentities.repository.PlayerRepository;
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
    private final PlayerStatsMapper playerStatsMapper;
    private final PlayerRepository playerRepository;

    @Override
    public void savePlayerStats(String match_id) {
        var player = userService.getPlayerConnected();
        PlayerStats playerStats = new PlayerStats();
        playerStats.setNumGoals(0);
        playerStats.setPlayer(player);
        playerStats.setMatch_id(match_id);
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
    public void updateGoalsScoredWhenMatchEnds(List<PlayerStatsDto> list, Match match) {
        for (PlayerStatsDto playerStatsDto : list) {
            var optionalPlayer = playerRepository.findById(playerStatsDto.getPlayer().getId());
            if (optionalPlayer.isEmpty()) {
                throw new IllegalArgumentException("Player not found !");
            }
            var player = optionalPlayer.get();
            checkPlayerAlreadyJoiningTheMatch(player, match);
            var playerStatOptional = playerStatsRepository.findByPlayer(player);
            if (playerStatOptional.isEmpty()) {
                throw new IllegalArgumentException("no player stat found !");
            }
            var playerStat = playerStatOptional.get();
            playerStat.setNumGoals(playerStatsDto.getNumGoals());
            playerStatsRepository.save(playerStat);
        }
    }

    private void checkPlayerAlreadyJoiningTheMatch(Player player, Match match) {
        if (player.getPlayersTeams().get(0).getTeam().getMatch().getId().equals(match.getId())) {
            throw new IllegalArgumentException("match_id_not_found");
        }
    }


}
