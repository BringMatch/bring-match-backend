package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.TeamPlayerDto;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.entities.TeamPlayer;
import com.example.testpfsentities.mapper.PlayerMapper;
import com.example.testpfsentities.mapper.TeamPlayerMapper;
import com.example.testpfsentities.repository.TeamPlayerRepository;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.service.PlayerStatsService;
import com.example.testpfsentities.service.TeamPlayerService;
import com.example.testpfsentities.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
@Slf4j
public class TeamPlayerServiceImpl implements TeamPlayerService {
    private final TeamPlayerRepository teamPlayerRepository;
    private final PlayerMapper playerMapper;
    private final UserService userService;
    private final PlayerService playerService;
    private final PlayerStatsService playerStatsService;

    private final TeamPlayerMapper teamPlayerMapper;

    @Override
    public void save(TeamPlayer teamPlayer) {
        teamPlayerRepository.save(teamPlayer);
    }


    @Override
    public void saveAll(Set<TeamPlayer> playersTeams) {
        teamPlayerRepository.saveAll(playersTeams);
    }

    @Override
    public TeamPlayer saveTeamPlayer(TeamPlayer teamPlayer, Team team) {
        Player player = userService.getPlayerConnected();
        teamPlayer.setPlayer(player);
        teamPlayer.setTeam(team);
//        playerStatsService.savePlayerStats();
        return teamPlayer;
//        return teamPlayerRepository.save(teamPlayer);
    }


    @Override
    public TeamPlayer getTeamPlayer() {
        return teamPlayerRepository.getTeamPlayer();
    }

    @Override
    public Collection<? extends TeamPlayer> map(List<TeamPlayerDto> teamPlayerDtos) {
        return teamPlayerMapper.toBo(teamPlayerDtos);
    }


    @Override
    public void assignTeamsPlayersToTeam(Team team, List<TeamPlayerDto> teamPlayerDtoList) {
        teamPlayerMapper.toBo(teamPlayerDtoList);
    }

    @Override
    public List<String> getCurrentTeamPositions() {
        var listTeamPlayer = teamPlayerRepository.findAll();
        List<String> finalCurrentTeamPosition = new ArrayList<>();
        for (TeamPlayer teamPlayer : listTeamPlayer) {
            finalCurrentTeamPosition.add(teamPlayer.getPosition());
        }
        return finalCurrentTeamPosition;
    }

    @Override
    public TeamPlayer saveDto(Team team ,TeamPlayerDto teamPlayerDto) {
        var teamPlayer = teamPlayerMapper.toBo(teamPlayerDto);
        var player = userService.getPlayerConnected();
        teamPlayer.setPlayer(player);
        teamPlayer.setTeam(team);
        teamPlayer.setMatch_owner(false);
        teamPlayer.setTeam_owner(false);
        return teamPlayerRepository.save(teamPlayer);
    }


}
