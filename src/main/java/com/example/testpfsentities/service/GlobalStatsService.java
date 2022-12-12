package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.GlobalStatsDto;
import com.example.testpfsentities.entities.GlobalStats;
import org.springframework.stereotype.Service;

@Service
public interface GlobalStatsService {
    GlobalStats getGlobalStatsById(long GlobalStatsId);
    void saveStats(GlobalStatsDto globalStatsDto);
}
