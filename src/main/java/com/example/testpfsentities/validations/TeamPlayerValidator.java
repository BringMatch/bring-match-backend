package com.example.testpfsentities.validations;

import com.example.testpfsentities.dto.TeamPlayerDto;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.entities.TeamPlayer;
import com.example.testpfsentities.repository.TeamPlayerRepository;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor()
@Slf4j
public class TeamPlayerValidator {
    private final PlayerService playerService;
    private final TeamPlayerRepository teamPlayerRepository;
    public void validateTeamPlayer(Player player, Team team, TeamPlayerDto teamPlayerDto) {
        playerService.checksPlayerExist(player);
        checksPlayerExistInTheTeam(player, team);
        checksLengthTeamInferiorOrEqualToMaxLengthTeam(team);
        checksPositionNameExistsInSystem(teamPlayerDto.getPosition().name());
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
            listPositionsInTeam.add(teamPlayer.getPosition().name());
        }
        if (listPositionsInTeam.contains(teamPlayerDto.getPosition())) {
            throw new IllegalArgumentException("this position already exists in the team ! please choose another position !");
        }
    }

    private void checksLengthTeamInferiorOrEqualToMaxLengthTeam(Team team) {
        var listTeamPlayers = getListPlayersInTeam(team.getId());
        if (team.getLength() == 0) {
            throw new IllegalArgumentException("Maximum team members is reached out !");
        }
    }

    private void checksPlayerExistInTheTeam(Player player, Team team) {
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
