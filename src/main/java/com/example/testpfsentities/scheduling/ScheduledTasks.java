package com.example.testpfsentities.scheduling;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.repository.MatchRepository;
import com.example.testpfsentities.service.MatchService;
import com.example.testpfsentities.service.PlayerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final MatchRepository matchRepository;
    private final PlayerService playerService;
    private final MatchService matchService;


    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    @Scheduled(cron = "* * * * * *")
    public void evaluateMatchAfterFourHour() {
        Date newDate = new Date(System.currentTimeMillis() - 3600 * 4000);

        //get the list of match that have the date dateTime
        try {
            List<Match> listMatches = matchRepository.findAll();
            for (Match match : listMatches) {
                if (match.getDate().getHours() == newDate.getHours()
                        && match.getDate().getMinutes() == newDate.getMinutes()
                        && match.getDate().getSeconds() == newDate.getSeconds()
                ) {
                    Player ownerMatchPlayer = playerService.returnOwnerMatchPlayer(match);
                    matchService.evaluateMatch(match, ownerMatchPlayer);
                }
            }
        } catch (Exception e) {
            log.info("no match has selected");
        }
    }


}
