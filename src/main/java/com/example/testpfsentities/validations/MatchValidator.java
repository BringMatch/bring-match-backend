package com.example.testpfsentities.validations;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.service.GroundService;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.service.UserService;
import com.example.testpfsentities.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor()
@Slf4j
public class MatchValidator {
    private final GroundService groundService;
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


        boolean open = groundService.getGroundOpenStatusById(ground);


        if (!open) {
            throw new IllegalArgumentException("the ground is now closed ! we cannot create a match for you !");
        }

        // check if the date is free for the match !
        checkGroundFree(matchDto, ground);

        Player player = userService.getPlayerConnected();
        if (player == null) {
            throw new IllegalArgumentException("player not exist");
        }


    }

    private void checkGroundFree(MatchDto matchDto, Ground ground) {
        List<Match> matchesList = ground.getMatches();
        for (Match match : matchesList) {
            var newDateAfterDuration = returnDateAfterDuration(match);
            log.info("this is the new date {}", newDateAfterDuration);
            if (matchDto.getDate().getTime() < newDateAfterDuration.getTime() &&
                    matchDto.getDate().getTime() >= match.getDate().getTime()
            ) {
                throw new IllegalArgumentException("match is already full in this time ! Please try another time");
            }
        }
    }

    private Date returnDateAfterDuration(Match match) {
        var dateMatch = match.getDate();
        Calendar date = Calendar.getInstance();
        date.setTime(dateMatch);
        long timeInSecs = date.getTimeInMillis();
        return new Date(timeInSecs + ((long) match.getDuration() * 60 * 1000));
    }
}
