package com.example.testpfsentities.service;

import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {
    void createPlayer();

    List<Player> getPlayers();
    void save(Player player);
    void joinTeam(Team team);
}
