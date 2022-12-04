package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.entities.TeamPlayer;
import com.example.testpfsentities.entities.composite.TeamPlayerKey;
import com.example.testpfsentities.mapper.PlayerMapper;
import com.example.testpfsentities.repository.TeamPlayerRepository;
import com.example.testpfsentities.service.TeamPlayerService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Data
public class TeamPlayerServiceImpl implements TeamPlayerService {
    private final TeamPlayerRepository teamPlayerRepository;
    private final PlayerMapper playerMapper;


    @Override
    public void save(TeamPlayer teamPlayer) {
        teamPlayerRepository.save(teamPlayer);
    }

    @Override
    public void saveAll(Set<TeamPlayer> playersTeams) {
        teamPlayerRepository.saveAll(playersTeams);
    }

    @Override
    public TeamPlayer createTeamPlayer(TeamPlayer teamPlayer,Team team ) {
       teamPlayer.setTeam(team);
        return teamPlayerRepository.save(teamPlayer);

    }

    @Override
    public void assignPlayersWithTeamsExisted(List<String> teams_id, List<PlayerDto> playerDtoList) {

    }

    @Override
    public void assignPlayersWithTeamsNotExisted(List<TeamDto> teamDtoList, List<PlayerDto> playerDtoList) {

    }
}
