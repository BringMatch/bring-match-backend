package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.PlayerStatsDto;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.PlayerStats;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<PlayerStats> toBo(List<PlayerStatsDto> matchDtos) {
        return matchDtos
                .stream()
                .map(element -> modelMapper.map(element, PlayerStats.class))
                .collect(Collectors.toList());
    }

    public List<PlayerStatsDto> toDto(List<PlayerStats> matches) {
        return matches
                .stream()
                .map(element -> modelMapper.map(element, PlayerStatsDto.class))
                .collect(Collectors.toList());
    }

}
