package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.entities.TeamPlayer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    Team createTeam(TeamDto teamDto);


    Team getTeamById(String team_id);

    void save(Team team);

    void assignPlayersWithTeams(List<Team> teamDtoList);

}

