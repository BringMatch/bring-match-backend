package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.TeamPlayerDto;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.entities.TeamPlayer;
import com.example.testpfsentities.mapper.PlayerMapper;
import com.example.testpfsentities.mapper.TeamPlayerMapper;
import com.example.testpfsentities.repository.TeamPlayerRepository;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.service.TeamPlayerService;
import com.example.testpfsentities.utils.StringUtils;
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
    private final PlayerService playerService;

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
        log.info("welcome");
        teamPlayer.setTeam(team);
        return teamPlayerRepository.save(teamPlayer);
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
    public void validateTeamPlayer(PlayerDto playerDto, Team team, TeamPlayerDto teamPlayerDto) {
        playerService.checksPlayerExist(playerDto);
        checksPlayerExistInTheTeam(playerDto, team);
        checksLengthTeamInferiorOrEqualToMaxLengthTeam(team);
        checksPositionNameExistsInSystem(teamPlayerDto.getPosition());
        checksPositionPlayerDtoFree(teamPlayerDto, team);
    }

    private void checksPositionNameExistsInSystem(String position) {
        if (!StringUtils.getListAvailablePositionsInATeam().contains(position)) {
            throw new IllegalArgumentException("position not existing in our db ! change the name of your position");
        }
    }

    private void checksPositionPlayerDtoFree(TeamPlayerDto teamPlayerDto, Team team) {
        var listTeamPlayers = team.getPlayersTeams();
        List<String> listPositionsInTeam = new ArrayList<>();
        for (TeamPlayer teamPlayer : listTeamPlayers) {
            listPositionsInTeam.add(teamPlayer.getPosition());
        }
        if (listPositionsInTeam.contains(teamPlayerDto.getPosition())) {
            throw new IllegalArgumentException("this position already exists in the team ! please choose another position !");
        }
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

    private void checksLengthTeamInferiorOrEqualToMaxLengthTeam(Team team) {
        var listTeamPlayers = getListPlayersInTeam(team.getId());
        if (team.getLength() <= listTeamPlayers.size()) {
            throw new IllegalArgumentException("Maximum team members is reached out !");
        }
    }

    private void checksPlayerExistInTheTeam(PlayerDto player, Team team) {
        var listTeamPlayers = getListPlayersInTeam(team.getId());
        listTeamPlayers.forEach(teamPlayer -> {
            if (teamPlayer.getPlayer().getId().equals(player.getId())) {
                throw new IllegalArgumentException("player already exists in the team !");
            }
        });
    }

    private List<TeamPlayer> getListPlayersInTeam(String team_id) {
        return teamPlayerRepository.findAll().stream()
                .filter(teamPlayer -> teamPlayer.getTeam().getId().equals(team_id))
                .collect(Collectors.toList());
    }

}
