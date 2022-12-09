package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.entities.composite.TeamPlayerKey;
import com.example.testpfsentities.mapper.PlayerMapper;
import com.example.testpfsentities.repository.*;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.service.TeamPlayerService;
import com.example.testpfsentities.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    @Lazy
    private final TeamPlayerService teamPlayerService;
    //    private final TeamService teamService;
    private final PlayerMapper playerMapper;

    @Override
    public void createPlayer() {
        Player player = new Player();
        player.setEmail("yessinejawa@gmail.com");
        player.setUpdatedAt(Date.from(Instant.now()));
        player.setPassword("yessine");
        player.setFirstName("ajaoua");
        player.setLastName("ajaqsdfoua");
        player.setPhoneNumber("45454");
        player.setRoleName("player");
        player.setCreatedAt(Date.from(Instant.now()));
        player.setNotificationPlayer(null);
        playerRepository.save(player);
    }

    @Override
    public List<PlayerDto> getPlayers() {
        return playerRepository.findAll().stream().map(playerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void save(PlayerDto playerDto) {
        playerRepository.save(playerMapper.toBo(playerDto));
    }

    @Override
    public Player findPlayerById(String player_id) {
        Optional<Player> optionalPlayer = playerRepository.findById(player_id);
        if (optionalPlayer.isEmpty()) {
            throw new IllegalArgumentException("player not found !");
        }
        return optionalPlayer.get();
    }


    @Override
    public Team assignPlayersWithTeams(Team team, List<String> players) {
//        List<TeamPlayer> teamPlayers = new ArrayList<>();
//        players.forEach(player_id -> {
//            Player player = this.findPlayerById(player_id);
//            TeamPlayer teamPlayer = teamPlayerService.createTeamPlayer(team, player);
//            teamPlayers.add(teamPlayer);
//        });
//        team.setPlayersTeams(teamPlayers);
        return null;
    }


}
