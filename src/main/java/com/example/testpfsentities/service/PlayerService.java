package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.PlayerDto;
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

    void createTeam(TeamDto teamDto);

    void createMatch(MatchDto matchDto);

    void joinMatchAsPlayer(TeamDto teamDto);

    void joinMatchAsTeam(PlayerDto playerDto);

    List<MatchDto> getMatches();

    void evaluateMatch(MatchDto matchDto);

}
