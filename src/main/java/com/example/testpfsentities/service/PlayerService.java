package com.example.testpfsentities.service;

import com.example.testpfsentities.entities.Team;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {
    void createPlayer();
    void joinTeam(Team team);
}
