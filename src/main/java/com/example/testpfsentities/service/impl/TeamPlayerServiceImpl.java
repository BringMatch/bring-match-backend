package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.dto.TeamPlayerDto;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.entities.TeamPlayer;
import com.example.testpfsentities.entities.composite.TeamPlayerKey;
import com.example.testpfsentities.mapper.PlayerMapper;
import com.example.testpfsentities.mapper.TeamPlayerMapper;
import com.example.testpfsentities.repository.TeamPlayerRepository;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.service.TeamPlayerService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public void assignPlayersWithTeamsExisted(List<String> teams_id, List<PlayerDto> playerDtoList) {

    }

    @Override
    public void assignPlayersWithTeamsNotExisted(List<TeamDto> teamDtoList, List<PlayerDto> playerDtoList) {

    }

    @Override
    public Collection<? extends TeamPlayer> map(List<TeamPlayerDto> teamPlayerDtos) {
        return teamPlayerMapper.toBo(teamPlayerDtos);
    }

    @Override
    public void checksPlayerExist(PlayerDto player, Team team) {
        playerService.checksPlayerExist(player);
        checksPlayerExistInTheTeam(player, team);
        checksLengthTeamInferiorOrEqualToMaxLengthTeam(team);
    }

    @Override
    public void assignTeamsPlayersToTeam(Team team, List<TeamPlayerDto> teamPlayerDtoList) {
        teamPlayerMapper.toBo(teamPlayerDtoList);
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
