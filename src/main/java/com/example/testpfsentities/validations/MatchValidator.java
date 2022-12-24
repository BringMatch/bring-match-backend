package com.example.testpfsentities.validations;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.service.GroundService;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.service.UserService;
import com.example.testpfsentities.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor()
@Slf4j
public class MatchValidator {
    private final GroundService groundService;
    private final PlayerService playerService;
    private final UserService userService;

    public void validateCreation(MatchDto matchDto) {
        // first we must check if the player's position already exist in our string utils or not !
        String position_given = matchDto.getTeams().get(0).getPlayersTeams().get(0).getPosition();
        if (!StringUtils.getListAvailablePositionsInATeam().contains(position_given)) {
            throw new IllegalArgumentException("position not existing in our system ! please try another one !");
        }

        // second we must check if the hour of that creation is available or not !
        // so we have to check if the hour is between the start and the end hour, and also we must check if the ground is closed or not !
        Ground ground = groundService.findByName(matchDto.getGroundName());

        int startHour = matchDto.getStartHour();
        int endHour = startHour + matchDto.getDuration();

        boolean ground_status = groundService.getGroundStatusById(ground);
        if (!ground_status) {
            throw new IllegalArgumentException("the ground is now closed ! we cannot create a match for you !");
        }
        if (startHour < ground.getStartHour()
                || endHour > ground.getEndHour()
                || startHour > ground.getEndHour()
                || endHour < ground.getStartHour()) {
            throw new IllegalArgumentException("check your start and end hour ! the ground finishes at " + ground.getEndHour());
        }
        Player player = userService.getPlayerConnected();
        if (player.equals(null)){
            throw new IllegalArgumentException("player not exist" );
        }
    }
}
