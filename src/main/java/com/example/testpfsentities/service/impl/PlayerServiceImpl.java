package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.PlayerSearchDto;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.entities.enums.Role;
import com.example.testpfsentities.mapper.PlayerMapper;
import com.example.testpfsentities.repository.*;
import com.example.testpfsentities.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Override
    public List<PlayerDto> getPlayers() {
        return playerRepository.findAll().stream().map(playerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void save(PlayerDto playerDto) {
        playerRepository.save(playerMapper.toBo(playerDto));
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
            throw new IllegalArgumentException("player not found !");
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
            throw new IllegalArgumentException("player not found !");
        }
        return optionalPlayer.get();
    }


}
