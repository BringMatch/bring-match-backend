package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.dto.TeamPlayerDto;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.entities.TeamPlayer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeamService {
    Team createTeam(TeamDto teamDto);


    void save(Team team);

    void assignPlayersWithTeams(List<Team> team, TeamDto teamDto);


    Team getTeamByName(String name);

    void assignTeamsPlayersToTeam(Team team, List<TeamPlayerDto> teamPlayerDtoList);


    List<String> getFreePositions(String team_id);

    void assignLengthTeamWithMatchLength(List<Team> teams , int length);

    Integer getLengthRemaining(String team_name);

    void setLengthTeamWithMaxLengthMatchWhenJoinAsTeam(Match match, MatchDto matchDto);

    boolean checksTeamByNameInMatch(String name , MatchDto matchDto);

    boolean checksTeamById(String id);

    void addNewPlayerToListPlayerTeams(Team team ,List<TeamPlayer> list, List<TeamPlayerDto> teamPlayerDtoList);

}

