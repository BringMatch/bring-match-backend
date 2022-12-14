package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.PlayerSearchDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {

    List<PlayerDto> getPlayers();
    void save(PlayerDto playerDto);

    Player returnOwnerMatchPlayer(Match match);

    void checksPlayerExist(Player player);

    List<PlayerDto> getPlayers(PlayerSearchDto playerSearchDto);

    Player findPlayerById(String id);

    Integer getGoalsScored();


    //int getTotalGoals();

}
