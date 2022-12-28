package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.*;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.Team;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public interface MatchService {
    void createMatch(MatchDto matchDto);

    List<MatchDto> getMatches();

    void evaluateMatch(EvaluationMatchDto evaluationMatchDto);

    Match findMatchById(String match_id);
    List<MatchDto> getMatchByDate(Date date);

    List<MatchDto> searchforMatches(MatchSearchDto matchSearchDto);

    void save(Match match);

    Match joinMatchAsTeam(MatchDto matchDto);

    Match joinMatchAsPlayer(MatchDto matchDto);

    void deleteMatch(MatchDto matchDto);


    Integer countNumberTeams(String match_id);

    List<MatchDto> getMatchesGround(String ground_name);

    Integer getNumberOwnerMatches(String owner_id);

    List<MatchDto> getMatchesOfOwnerGrounds(MatchDto matchDto);

    String getMatchCode(String match_id);

    MatchDto getMatchById(String match_id);

    Integer getLengthTeamInMatch(String match_id);

    List<Match> getPendingMatches();

    Integer getNumberMatchesOfPlayer();

    Integer getNumberMatchesWinOfPlayer();

    Integer getNumberMatchesLoseOfPlayer();

    Integer getNumberMatchesDrawOfPlayer();

    List<MatchDto> getMatchesByPlayer();

    void sendNotificationOfMatchEvaluation(Match match,Player player);
}
