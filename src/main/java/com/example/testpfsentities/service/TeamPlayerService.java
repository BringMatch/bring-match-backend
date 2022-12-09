package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.entities.TeamPlayer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface TeamPlayerService {
    void save(TeamPlayer teamPlayer);

    void saveAll(Set<TeamPlayer> playersTeams);

    TeamPlayer createTeamPlayer(TeamPlayer teamPlayer,Team team);
    TeamPlayer getTeamPlayer();

    void assignPlayersWithTeamsExisted(List<String> teams_id, List<PlayerDto> playerDtoList);

    void assignPlayersWithTeamsNotExisted(List<TeamDto> teamDtoList, List<PlayerDto> playerDtoList);

}
