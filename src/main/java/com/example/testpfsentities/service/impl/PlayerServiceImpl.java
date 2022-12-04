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
        player.setUpdatedAt(LocalDateTime.now());
        player.setPassword("yessine");
        player.setFirstName("ajaoua");
        player.setLastName("ajaqsdfoua");
        player.setPhoneNumber("45454");
        player.setRoleName("player");
        player.setCreatedAt(LocalDateTime.now());
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
    public void joinMatchAsPlayer(PlayerDto playerDto) {

//        String team_id = playerDto.getTeam_id();
//        Team team = teamService.getTeamById(team_id);
//
//        String player_id = playerDto.getId();
//        Optional<Player> playerOptional = playerRepository.findById(player_id);
//        if (playerOptional.isEmpty()) {
//            throw new IllegalArgumentException("player not existing !");
//        }
//        Player player = playerOptional.get();
//
//        TeamPlayerKey teamPlayerKey = new TeamPlayerKey();
//        teamPlayerKey.setPlayer_id(player_id);
//        teamPlayerKey.setTeam_id(team_id);
//        TeamPlayer teamPlayer = new TeamPlayer();
//        teamPlayer.setTeamPlayerKey(teamPlayerKey);
//        teamPlayer.setTeam(team);
//        teamPlayer.setPlayer(player);
//        teamPlayer.setTeam_owner(false);
//        teamPlayer.setMatch_owner(false);

//        Set<TeamPlayer> teamPlayers = team.getPlayersTeams();
//        teamPlayers.add(teamPlayer);
//        team.setPlayersTeams(Set.of(teamPlayer));
//
//        teamPlayerService.save(teamPlayer);
//        teamService.save(team);

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
