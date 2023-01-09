package com.example.testpfsentities.validations;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.TeamPlayerDto;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.exceptions.BusinessException;
import com.example.testpfsentities.exceptions.Message;
import com.example.testpfsentities.repository.MatchRepository;
import com.example.testpfsentities.service.*;
import com.example.testpfsentities.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor()
@Slf4j
public class TeamValidator {
    private final MatchRepository matchRepository;
    private final TeamService teamService;
    private final UserService userService;
    private final TeamPlayerValidator teamPlayerValidator;

    public void validateInsertionTeam(MatchDto matchDto, Match match) {

        if (match.getTeams().size() == 2) {
            throw new BusinessException(new Message("you cannot join match ! it has already two teams !"));
        }

        // 0 : we must check if the match is private the user must provide the right code
        if (match.getPrivateMatch()) {
            if (!matchDto.getMatchCode().equals(match.getMatchCode())) {
                throw new BusinessException(new Message("please check the code before you join the match !"));
            }
        }

        // first we must check if the team's name already exist in the match teams list !
        List<Team> teamList = match.getTeams();
        String team_name_in_match_dto = matchDto.getTeams().get(0).getName();
        for (Team team : teamList) {
            if (team.getName().equals(team_name_in_match_dto)) {
                throw new BusinessException(new Message("Team Already Exist in the match ! Please try another name for your team !"));
            }
        }

        // second we must check if the player's position already exist in our string utils or not !
        String position_given = matchDto.getTeams().get(0).getPlayersTeams().get(0).getPosition().name();
        if (!StringUtils.getListAvailablePositionsInATeam().contains(position_given)) {
            throw new IllegalArgumentException("position not existing in our system ! please try another one !");
        }
    }

    public void validateInsertionPlayerInTeam(Match match, MatchDto matchDto) {
        var teamDto = matchDto.getTeams().get(0);
        if (!teamService.checksTeamByNameInMatch(teamDto.getName(), matchDto)) {
            throw new IllegalArgumentException("name of team not existing !");
        }
        if (!teamService.checksTeamById(teamDto.getId())) {
            throw new IllegalArgumentException("id team not found !");
        }

        if (match.getPrivateMatch()) {
            if (!matchDto.getMatchCode().equals(match.getMatchCode())) {
                throw new IllegalArgumentException("please check the code before you join the match !");
            }
        }

        var team = match.getTeams().stream()
                .filter(teamed -> teamed.getName().equals(matchDto.getTeams().get(0).getName()))
                .collect(Collectors.toList()).get(0);
        if (team.getLength() == 0) {
            throw new IllegalArgumentException("sorry you cannot join this match ! full positions");
        }
        var listTeamPresentInMatch = match.getTeams();

        List<TeamPlayerDto> teamPlayerDtos = matchDto.getTeams().get(0).getPlayersTeams();
        Player player = userService.getPlayerConnected();
        listTeamPresentInMatch.forEach(
                currentTeam ->
                        teamPlayerDtos.forEach(
                                teamPlayerDto -> teamPlayerValidator
                                        .validateTeamPlayer(player, currentTeam, teamPlayerDto)));
    }

}
