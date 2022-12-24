package com.example.testpfsentities.validations;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor()
@Slf4j
public class TeamValidator {
    private final PlayerService playerService;

    public void validateInsertionTeam(MatchDto matchDto, Match match) {


        // 0 : we must check if the match is private the user must provide the right code
        if (match.getPrivateMatch()) {
            if (!matchDto.getMatchCode().equals(match.getMatchCode())) {
                throw new IllegalArgumentException("please check the code before you join the match !");
            }
        }

        // first we must check if the team's name already exist in the match teams list !
        List<Team> teamList = match.getTeams();
        String team_name_in_match_dto = matchDto.getTeams().get(0).getName();
        for (Team team : teamList) {
            if (team.getName().equals(team_name_in_match_dto)) {
                throw new IllegalArgumentException("Team Already Exist in the match ! Please try another name for your team !");
            }
        }

        // second we must check if the player's position already exist in our string utils or not !
        String position_given = matchDto.getTeams().get(0).getPlayersTeams().get(0).getPosition();
        if (!StringUtils.getListAvailablePositionsInATeam().contains(position_given)) {
            throw new IllegalArgumentException("position not existing in our system ! please try another one !");
        }

        if (matchDto.getMatchCode() == null) {
            throw new IllegalArgumentException("please provide the code before joining the match !");
        }

        //playerService.checksPlayerExist(matchDto.getTeams().get(0).getPlayersTeams().get(0).getPlayer());
    }

}
