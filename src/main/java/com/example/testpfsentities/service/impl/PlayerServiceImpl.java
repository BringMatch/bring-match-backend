package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.PlayerSearchDto;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.exceptions.BusinessException;
import com.example.testpfsentities.exceptions.Message;
import com.example.testpfsentities.mapper.PlayerMapper;
import com.example.testpfsentities.repository.*;
import com.example.testpfsentities.service.PlayerStatsService;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final UserService userService;
    private final PlayerStatsService playerStatsService;

    @Override
    public List<PlayerDto> getPlayers() {
        return playerRepository.findAll().stream().map(playerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void save(PlayerDto playerDto) {
        playerRepository.save(playerMapper.toBo(playerDto));
        userService.create(playerDto);
    }

    @Override
    public Player returnOwnerMatchPlayer(Match match) {
        Player player = new Player();
        for (Team team : match.getTeams()) {
            for (TeamPlayer teamPlayer : team.getPlayersTeams()) {
                if (teamPlayer.isMatch_owner()) {
                    player = teamPlayer.getPlayer();
                }
            }
        }
        return player;
    }


    @Override
    public void checksPlayerExist(Player player) {
        Optional<Player> optionalPlayer = playerRepository.findById(player.getId());
        if (optionalPlayer.isEmpty()) {
            throw new BusinessException(new Message("player not found !"));
        }
    }

    @Override
    public List<PlayerDto> getPlayers(PlayerSearchDto playerSearchDto) {
        return playerMapper.toDto(playerRepository.findByFirstNameAndTownAndRegionAndLastName(
                playerSearchDto.getFirstName(),
                playerSearchDto.getLastName(),
                playerSearchDto.getTown(),
                playerSearchDto.getRegion())
        );
    }

    @Override
    public Player findPlayerById(String id) {
        Optional<Player> optionalPlayer = playerRepository.findById(id);
        if (optionalPlayer.isEmpty()) {
            throw new BusinessException(new Message("player not found !"));
        }
        return optionalPlayer.get();
    }

    @Override
    public Integer getGoalsScored() {
        var player = userService.getPlayerConnected();
        return playerStatsService.getGoalsScoredByPlayer(player);
    }


}
