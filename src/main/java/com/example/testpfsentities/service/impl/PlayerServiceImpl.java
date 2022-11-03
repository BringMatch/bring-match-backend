package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.repository.AdminRepository;
import com.example.testpfsentities.repository.PlayerRepository;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.service.TeamService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final AdminRepository adminRepository;
    private final PlayerRepository playerRepository;
    private final TeamService teamService;

    public PlayerServiceImpl(AdminRepository adminRepository, PlayerRepository playerRepository, TeamService teamService) {
        this.adminRepository = adminRepository;
        this.playerRepository = playerRepository;
        this.teamService = teamService;
    }

    @Override
    public void createPlayer() {
        Admin admin = adminRepository.findById(1L).get();
        Player player = new Player();
        player.setEmail("yessinejawa@gmail.com");
        player.setUpdatedAt(Instant.now());
        player.setUserPassword("yessine");
        player.setFirstName("ajaoua");
        player.setLastName("ajaqsdfoua");
        player.setPhoneNumber("45454");
        player.setRoleName("player");
        player.setCreatedAt(Instant.now());
        player.setMatch_owner(true);
        player.setTeams(null);
        player.setNotificationPlayer(null);
        player.setStatisticsPlayers(null);
        playerRepository.save(player);
    }

    @Override
    public void joinTeam(Team team) {
        teamService.createTeam();
    }


}
