package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.PlayerSearchDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {
    void createPlayer();

    List<PlayerDto> getPlayers();
    void save(PlayerDto playerDto);

    Player findPlayerById(String player_id);


    Team assignPlayersWithTeams(Team team, List<String> players);

    void checksPlayerExist(PlayerDto player);

    List<PlayerDto> getPlayers(PlayerSearchDto playerSearchDto);
}
