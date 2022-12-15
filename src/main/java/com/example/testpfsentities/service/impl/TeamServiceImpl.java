package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.dto.TeamPlayerDto;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.mapper.TeamMapper;
import com.example.testpfsentities.repository.TeamRepository;
import com.example.testpfsentities.service.TeamPlayerService;
import com.example.testpfsentities.service.TeamService;
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

    @Override
    public Team createTeam(TeamDto teamDto) {
        return teamMapper.toBo(teamDto);
    }


    @Override
    public void save(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void validateInsertionPlayer(Team team, List<TeamPlayerDto> teamPlayerDtos) {
        teamPlayerDtos.forEach(teamPlayerDto -> teamPlayerService
                .validateTeamPlayer(teamPlayerDto.getPlayer(), team, teamPlayerDto));
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
    public void assignPlayersWithTeams(List<Team> teams, TeamDto teamDto) {
        Team team = teams.stream()
                .filter(teamElement -> teamElement.getName().equals(teamDto.getName()))
                .collect(Collectors.toList()).get(0);

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
}
