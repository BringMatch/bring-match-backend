package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.dto.TeamPlayerDto;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.entities.TeamPlayer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public interface TeamPlayerService {
    void save(TeamPlayer teamPlayer);

    void saveAll(Set<TeamPlayer> playersTeams);

    TeamPlayer saveTeamPlayer(TeamPlayer teamPlayer,Team team);

    void assignPlayersWithTeamsExisted(List<String> teams_id, List<PlayerDto> playerDtoList);

    void assignPlayersWithTeamsNotExisted(List<TeamDto> teamDtoList, List<PlayerDto> playerDtoList);

    Collection<? extends TeamPlayer> map(List<TeamPlayerDto> teamPlayerDtos);

}
