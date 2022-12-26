package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.PlayerStatsDto;
import com.example.testpfsentities.entities.PlayerStats;
import com.example.testpfsentities.mapper.PlayerStatsMapper;
import com.example.testpfsentities.repository.PlayerStatsRepository;
import com.example.testpfsentities.service.PlayerSatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlayerStatsServiceImpl implements PlayerSatsService {
    private final PlayerStatsRepository playerStatsRepository;
    private final PlayerStatsMapper mapper;
    @Override
    public PlayerStats savePlayerStats(PlayerStatsDto playerStatsDto) {
         return playerStatsRepository.save(mapper.toBo(playerStatsDto));

    }


}
