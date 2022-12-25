package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.GlobalStatsDto;
import com.example.testpfsentities.entities.GlobalStats;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.mapper.GlobalStatsMapper;
import com.example.testpfsentities.repository.GlobalStatsRepository;
import com.example.testpfsentities.service.GlobalStatsService;
import com.example.testpfsentities.service.MatchService;
import com.example.testpfsentities.service.NotificationPlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GlobalStatsServiceImpl implements GlobalStatsService {

    private final GlobalStatsRepository globalStatsRepository;
    private final GlobalStatsMapper globalStatsMapper;
    private final NotificationPlayerService notificationPlayerService;
    private final MatchService matchService;

    @Override
    public GlobalStats getGlobalStatsById(long GlobalStatsId) {
        Optional<GlobalStats> optionalGlobalStats = globalStatsRepository.findById(GlobalStatsId);
        if (optionalGlobalStats.isEmpty()) {
            throw new IllegalArgumentException("global Stat not existing !");
        }

        return optionalGlobalStats.get();
    }


    @Override
    public void saveStats(GlobalStatsDto globalStatsDto, String notification_player_id) {
        var globalStat = globalStatsMapper.toBo(globalStatsDto);
        String match_id = globalStatsDto.getMatch().getId();
        globalStat.setMatch(matchService.findMatchById(match_id));
        Long teamOneGoals = globalStatsDto.getNumGoalsTeamOne();
        Long teamTwoGoals = globalStatsDto.getNumGoalsTeamTwo();
        String final_score = teamOneGoals + "-" + teamTwoGoals;
        globalStat.setFinalScore(final_score);
        Match match = matchService.findMatchById(match_id);

        if (Objects.equals(teamOneGoals, teamTwoGoals)) {
            match.setDraw(true);
        } else {
            match.setDraw(false);
        }

        globalStatsRepository.save(globalStat);
        notificationPlayerService.updateNotificationState(notification_player_id);
    }

    @Override
    public List<GlobalStatsDto> getAllGlobalStats() {
        return globalStatsMapper.toDto(globalStatsRepository.findAll());
    }
}
