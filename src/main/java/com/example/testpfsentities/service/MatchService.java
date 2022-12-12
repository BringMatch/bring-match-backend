package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.*;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.entities.TeamPlayer;
import com.example.testpfsentities.entities.composite.TeamPlayerKey;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface MatchService {
    void createMatch(MatchDto matchDto);

    List<MatchDto> getMatches();

    void evaluateMatch(MatchDto matchDto);

    Match findMatchById(MatchDto matchDto);

    void save(Match match);

    Match setTeams(Match match , Team team);

    Match joinMatchAsTeam(MatchDto matchDto);

    Match joinMatchAsPlayer(MatchDto matchDto);

    void deleteMatch(MatchDto matchDto);


    Integer countNumberTeams(String match_id);

    List<MatchDto> getMatchesGround(String ground_name);

    Integer getNumberOwnerMatches(String owner_id);
}
