package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.GlobalStatsDto;
import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.entities.GlobalStats;
import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.entities.Match;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class GlobalStatsMapper {
    private final ModelMapper modelMapper;

    public GlobalStats toBo(GlobalStatsDto globalStatsDto) {
        return modelMapper.map(globalStatsDto, GlobalStats.class);
    }

    public GlobalStatsDto toDto(GlobalStats globalStats) {
        return modelMapper.map(globalStats, GlobalStatsDto.class);
    }

    public List<GlobalStatsDto> toDto(List<GlobalStats> globalStats) {
        return globalStats
                .stream()
                .map(element -> modelMapper.map(element, GlobalStatsDto.class))
                .collect(Collectors.toList());
    }
}
