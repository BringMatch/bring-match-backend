package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.repository.TeamRepository;
import com.example.testpfsentities.service.TeamService;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamService;

    public TeamServiceImpl(TeamRepository teamService) {
        this.teamService = teamService;
    }

    @Override
    public Team createTeam() {
        return null;
    }
}
