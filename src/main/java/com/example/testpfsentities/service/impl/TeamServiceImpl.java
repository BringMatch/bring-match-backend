package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.dto.TeamPlayerDto;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.entities.TeamPlayer;
import com.example.testpfsentities.entities.enums.MatchResult;
import com.example.testpfsentities.mapper.TeamMapper;
import com.example.testpfsentities.repository.MatchRepository;
import com.example.testpfsentities.repository.TeamRepository;
import com.example.testpfsentities.service.TeamPlayerService;
import com.example.testpfsentities.service.TeamService;
import com.example.testpfsentities.service.UserService;
import com.example.testpfsentities.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    private final TeamPlayerService teamPlayerService;
    private final MatchRepository matchRepository;

    @Override
    public Team createTeam(TeamDto teamDto) {
        return teamMapper.toBo(teamDto);
    }


    @Override
    public void save(Team team) {
        teamRepository.save(team);
    }


    @Override
    public Team getTeamByName(String name) {
        Optional<Team> teamOptional = teamRepository.findByName(name);
        if (teamOptional.isEmpty()) {
            throw new IllegalArgumentException("team not found !");
        }
        return teamOptional.get();
    }

    @Override
    public void assignTeamsPlayersToTeam(Team team, List<TeamPlayerDto> teamPlayerDtoList) {
        teamPlayerService.assignTeamsPlayersToTeam(team, teamPlayerDtoList);
    }

    @Override
    public List<String> getFreePositions(String team_name) {
        getTeamByName(team_name);
        var listAllPositions = StringUtils.getListAvailablePositionsInATeam();
        var listCurrentTeamPositions = teamPlayerService.getCurrentTeamPositions();
        List<String> freePositionsList = new ArrayList<>();
        for (String current : listCurrentTeamPositions) {
            for (String all : listAllPositions) {
                if (!current.equals(all)) {
                    freePositionsList.add(all);
                }
            }
        }
        return freePositionsList;
    }

    @Override
    public Integer getLengthRemaining(String team_name) {
        Team team = getTeamByName(team_name);
        return team.getLength();
    }

    @Override
    public void setLengthTeamWithMaxLengthMatchWhenJoinAsTeam(Match match, MatchDto matchDto) {
        int maxNumberPlayer = match.getNumberTeamPlayers();
        var teams = match.getTeams();
        String currentTeamName = matchDto.getTeams().get(0).getName();
        for (Team team : teams) {
            if (team.getName().equals(currentTeamName)) {
                team.setLength(maxNumberPlayer - 1);
                break;
            }
        }
    }

    @Override
    public boolean checksTeamByNameInMatch(String name, MatchDto matchDto) {
        Optional<Match> optionalMatch = matchRepository.findById(matchDto.getId());
        if (optionalMatch.isEmpty()) {
            throw new IllegalArgumentException("no match found !");
        }
        var listTeamsInMatch = optionalMatch.get().getTeams();
        for (Team team : listTeamsInMatch) {
            if (team.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checksTeamById(String id) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        return teamOptional.isPresent();
    }

    @Override
    public void addNewPlayerToListPlayerTeams(Team team, List<TeamPlayer> list, TeamPlayerDto teamPlayerDto) {
        var teamPlayer = teamPlayerService.save(team, teamPlayerDto);
        list.add(teamPlayer);
    }


    @Override
    public void assignPlayersWithTeams(List<Team> teams, TeamDto teamDto) {

        Team team = teams.stream()
                .filter(teamElement -> teamElement.getName().equals(teamDto.getName()))
                .collect(Collectors.toList()).get(0);
        log.info("this the team name of second team {}" , team.getName());

        boolean updateMatchOwner = teams.size() == 1;
        boolean updateTeamOwner = team.getPlayersTeams().size() == 1;

        team.getPlayersTeams().forEach(teamPlayer -> {
            if (updateMatchOwner) {
                teamPlayer.setTeam_owner(true);
                teamPlayer.setMatch_owner(true);
            } else if (updateTeamOwner) {
                teamPlayer.setMatch_owner(false);
                teamPlayer.setTeam_owner(true);
            } else {
                teamPlayer.setMatch_owner(false);
                teamPlayer.setTeam_owner(false);
            }
            teamPlayerService.saveTeamPlayer(teamPlayer, team);
        });
    }

    @Override
    public void assignPlayersWithTeamsWhenJoinAsTeam(List<Team> teams, TeamDto teamDto) {
        Team team = teams.stream()
                .filter(teamElement -> teamElement.getName().equals(teamDto.getName()))
                .collect(Collectors.toList()).get(0);

        log.info("this the team name of second team {}" , team.getName());

        boolean updateMatchOwner = teams.size() == 1;
        boolean updateTeamOwner = team.getPlayersTeams().size() == 1;

        team.getPlayersTeams().forEach(teamPlayer -> {
            if (updateMatchOwner) {
                teamPlayer.setTeam_owner(true);
                teamPlayer.setMatch_owner(true);
            } else if (updateTeamOwner) {
                teamPlayer.setMatch_owner(false);
                teamPlayer.setTeam_owner(true);
            } else {
                teamPlayer.setMatch_owner(false);
                teamPlayer.setTeam_owner(false);
            }
            teamPlayerService.saveTeamPlayerWhenJoinAsTeam(teamPlayer, team);
        });
    }
}
