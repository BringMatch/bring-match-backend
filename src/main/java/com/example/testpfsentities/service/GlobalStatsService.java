package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.GlobalStatsDto;
import com.example.testpfsentities.entities.GlobalStats;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GlobalStatsService {
    GlobalStats getGlobalStatsById(long GlobalStatsId);
    void saveStats(GlobalStatsDto globalStatsDto , String notification_player_id);

    List<GlobalStatsDto> getAllGlobalStats();
}
