package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.GlobalStatsDto;
import com.example.testpfsentities.entities.GlobalStats;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.entities.enums.MatchResult;
import com.example.testpfsentities.mapper.GlobalStatsMapper;
import com.example.testpfsentities.repository.GlobalStatsRepository;
import com.example.testpfsentities.repository.MatchRepository;
import com.example.testpfsentities.service.GlobalStatsService;
import com.example.testpfsentities.service.MatchService;
import com.example.testpfsentities.service.NotificationPlayerService;
import com.example.testpfsentities.service.TeamService;
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
    private final TeamService teamService;
    private final MatchRepository matchRepository;

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

        var match = matchService.findMatchById(match_id);

        globalStat.setMatch(match);
        var teamOneDto = globalStatsDto.getMatch().getTeams().get(0);
        var teamTwoDto = globalStatsDto.getMatch().getTeams().get(1);

        Team teamOne = teamService.getTeamByName(teamOneDto.getName());
        Team teamTwo = teamService.getTeamByName(teamTwoDto.getName());
        int teamOneGoals = teamOneDto.getNumberGoals();
        int teamTwoGoals = teamTwoDto.getNumberGoals();

        String final_score = teamOneGoals + "-" + teamTwoGoals;
        globalStat.setFinalScore(final_score);

        if (Objects.equals(teamOneGoals, teamTwoGoals)) {
            match.setDraw(true);
        } else {
            match.setDraw(false);
            if (teamOneGoals < teamTwoGoals) {
                teamTwo.setMatchResult(MatchResult.WIN);
            } else {
                teamOne.setMatchResult(MatchResult.WIN);
            }
        }
        matchRepository.save(match);
        globalStatsRepository.save(globalStat);
        notificationPlayerService.updateNotificationState(notification_player_id);
    }

    @Override
    public List<GlobalStatsDto> getAllGlobalStats() {
        return globalStatsMapper.toDto(globalStatsRepository.findAll());
    }
}
