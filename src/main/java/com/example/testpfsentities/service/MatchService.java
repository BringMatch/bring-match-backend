package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.MatchSearchDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.Team;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface MatchService {
    void createMatch(MatchDto matchDto);

    List<MatchDto> getMatches();

    void evaluateMatch(Match match);

    Match findMatchById(String match_id);
    List<Match> getMatchByDate(Date date);

    List<MatchDto> getMatchsByRegionAndTown(MatchSearchDto matchSearchDto);

    void save(Match match);

    Match setTeams(Match match , Team team);


    Match joinMatchAsTeam(MatchDto matchDto);

    Match joinMatchAsPlayer(MatchDto matchDto);

    void deleteMatch(MatchDto matchDto);


    Integer countNumberTeams(String match_id);

    List<MatchDto> getMatchesGround(String ground_name);

    Integer getNumberOwnerMatches(String owner_id);

    List<MatchDto> getMatchesOfOwnerGrounds(MatchDto matchDto);

    String getMatchCode(String match_id);
}
