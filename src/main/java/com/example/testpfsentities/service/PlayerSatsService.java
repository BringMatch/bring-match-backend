package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.PlayerStatsDto;
import com.example.testpfsentities.entities.PlayerStats;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface PlayerSatsService {
    PlayerStats savePlayerStats(PlayerStatsDto playerStatsDto);
}
