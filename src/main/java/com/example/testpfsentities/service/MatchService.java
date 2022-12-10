package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.Team;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface MatchService {
    void createMatch(MatchDto matchDto);

    List<Match> getMatches();

   void evaluateMatch(Match match);

    Match findMatchById(String match_id);
   List<Match> getMatchByDate(Date date);

    void save(Match match);

    Match setTeams(Match match , Team team);

    public void joinMatchAsTeam(MatchDto matchDto);

    void deleteMatch(MatchDto matchDto);

    List<MatchDto> getMatchesGround(String ground_id);
}
