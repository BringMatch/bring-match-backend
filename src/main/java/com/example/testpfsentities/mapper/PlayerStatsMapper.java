package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.PlayerStatsDto;
import com.example.testpfsentities.entities.PlayerStats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PlayerStatsMapper {
    private final ModelMapper modelMapper;
    public PlayerStats toBo(PlayerStatsDto playerStatsDto) {
        return modelMapper.map(playerStatsDto, PlayerStats.class);
    }

    public PlayerStatsDto toDto(PlayerStats playerStats) {
        return modelMapper.map(playerStats, PlayerStatsDto.class);
    }

}
