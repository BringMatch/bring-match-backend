package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.GlobalStatsDto;
import com.example.testpfsentities.entities.GlobalStats;
import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.mapper.GlobalStatsMapper;
import com.example.testpfsentities.repository.GlobalStatsRipository;
import com.example.testpfsentities.service.GlobalStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GlobalStatsServiceImpl implements GlobalStatsService {

    private final GlobalStatsRipository globalStatsRipository;
    private final GlobalStatsMapper mapper;

    @Override
    public GlobalStats getGlobalStatsById(long GlobalStatsId) {
        Optional<GlobalStats> optionalGlobalStats = globalStatsRipository.findById(GlobalStatsId);
        if (optionalGlobalStats.isEmpty()) {
            throw new IllegalArgumentException("ground not existing !");
        }

        return optionalGlobalStats.get();
    }


    @Override
    public void saveStats(GlobalStatsDto globalStatsDto) {
        globalStatsRipository.save(mapper.toBo(globalStatsDto));
    }
}
