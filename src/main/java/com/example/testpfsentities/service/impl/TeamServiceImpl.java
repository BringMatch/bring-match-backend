package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.mapper.TeamMapper;
import com.example.testpfsentities.repository.TeamRepository;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.service.TeamPlayerService;
import com.example.testpfsentities.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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
    public Team getTeamById(String team_id) {
        Optional<Team> teamOptional = teamRepository.findById(team_id);
        if (teamOptional.isEmpty()) {
            throw new IllegalArgumentException("team not found !");
        }
        return teamOptional.get();
    }

    @Override
    public void save(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void assignPlayersWithTeams(List<Team> teamList) {


        teamList.forEach(team -> {
            team.getPlayersTeams().forEach(teamPlayer -> {
                teamPlayerService.createTeamPlayer(teamPlayer,team );
            });
        });

    }
//        List<Team> list = new ArrayList<>();
//        teamDtoList.forEach(teamDto -> {
//            Team team;
//            team = teamMapper.toBo(teamDto);
//            Team newTeam = playerService.assignPlayersWithTeams(team, teamDto.getPlayers());
//            newTeam.setCreatedAt(LocalDateTime.now());
//            newTeam.setUpdatedAt(LocalDateTime.now());
//            list.add(newTeam);
//            log.info("this is the team number {} ", newTeam);
//            teamRepository.save(newTeam);
//        });




}
