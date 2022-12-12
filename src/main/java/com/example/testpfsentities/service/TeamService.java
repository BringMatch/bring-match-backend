package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.dto.TeamPlayerDto;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.entities.TeamPlayer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    Team createTeam(TeamDto teamDto);


    Team getTeamById(MatchDto matchDto);

    void save(Team team);

    void assignPlayersWithTeams(List<Team> team , TeamDto teamDto);

    void checkPlayersExist(Team team, List<TeamPlayerDto> listDto);

    Team getTeamByName(String name);

    void assignTeamsPlayersToTeam(Team team, List<TeamPlayerDto> teamPlayerDtoList);

}

