package com.example.testpfsentities.scheduling;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.entities.enums.MatchStatus;
import com.example.testpfsentities.repository.MatchRepository;
import com.example.testpfsentities.service.MatchService;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private final MatchRepository matchRepository;
    private final PlayerService playerService;
    private final MatchService matchService;


//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() {
//        log.info("The time is now {}", dateFormat.format(new Date()));
//    }

//    @Scheduled(cron = "* * * * * *")
//    public void evaluateMatchAfterOneHour() {
//        Date currentDate = new Date(System.currentTimeMillis() - 3600 * 1500);
//        Calendar cal1 = Calendar.getInstance();
//        cal1.setTime(currentDate);
//        int currentYear = cal1.get(Calendar.YEAR);
//        int currentMonth = cal1.get(Calendar.MONTH);
//        int currentDay = cal1.get(Calendar.DAY_OF_MONTH);
//        //get the list of match that have the date dateTime
//        try {
//            List<Match> listMatches = matchRepository.findAll();
//            for (Match match : listMatches) {
//                Calendar cal2 = Calendar.getInstance();
//                cal2.setTime(match.getDate());
//                int otherYear = cal2.get(Calendar.YEAR);
//                int otherMonth = cal2.get(Calendar.MONTH);
//                int otherDay = cal2.get(Calendar.DAY_OF_MONTH);
//                if (currentYear == otherYear && currentMonth == otherMonth && currentDay == otherDay) {
//                    if (match.getDate().getHours() == currentDate.getHours()
//                            && match.getDate().getMinutes() == currentDate.getMinutes()
//                            && match.getDate().getSeconds() == currentDate.getSeconds()
//                    ) {
//                        Player ownerMatchPlayer = playerService.returnOwnerMatchPlayer(match);
//                        matchService.sendNotificationOfMatchEvaluation(match, ownerMatchPlayer);
//                        match.setMatchStatus(MatchStatus.PLAYED);
//                        matchService.save(match);
//                    }
//                }
//
//            }
//        } catch (Exception e) {
//            log.info("no match has selected");
//        }
//    }

//    @Scheduled(cron = "* * * * * *")
//    public void changeMatchStatusToPending() {
//        Date currentDate = new Date();
//        Calendar cal1 = Calendar.getInstance();
//        cal1.setTime(currentDate);
//        int currentYear = cal1.get(Calendar.YEAR);
//        int currentMonth = cal1.get(Calendar.MONTH);
//        int currentDay = cal1.get(Calendar.DAY_OF_MONTH);
//        try {
//            List<Match> listMatches = matchRepository.findAll();
//            for (Match match : listMatches) {
//                Calendar cal2 = Calendar.getInstance();
//                cal2.setTime(match.getDate());
//                int otherYear = cal2.get(Calendar.YEAR);
//                int otherMonth = cal2.get(Calendar.MONTH);
//                int otherDay = cal2.get(Calendar.DAY_OF_MONTH);
//                //add duration to start date of the match
//                cal2.add(Calendar.MINUTE, match.getDuration());
//                Date DatePlusDuration = cal2.getTime();
//                if (currentYear == otherYear && currentMonth == otherMonth && currentDay == otherDay) {
//                    if (currentDate.after(match.getDate()) && currentDate.before(DatePlusDuration)) {
//                        match.setMatchStatus(MatchStatus.PENDING);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            log.info("no match is playing now");
//        }
//    }

    @Scheduled(cron = "* * * * * *")
    public void updateMatchStatusAndSendNotificationToEvaluateMatch() {
        var matchesList = matchRepository.findAll();
        Date date = new Date();
        for (Match match : matchesList) {
            var newDateAfterDuration = DateUtils.returnDateAfterDuration(match.getDate(), match.getDuration());
            if (DateUtils.compareTwoDates(match.getDate(), date) && DateUtils.compareTwoDates(date, newDateAfterDuration) && match.getMatchStatus().equals(MatchStatus.NOT_PLAYED)) {
                match.setMatchStatus(MatchStatus.PENDING);
                matchService.save(match);
            } else if (DateUtils.compareTwoDates(newDateAfterDuration, date) && match.getMatchStatus().equals(MatchStatus.PENDING)) {
                match.setMatchStatus(MatchStatus.PLAYED);
                matchService.save(match);
                if (DateUtils.compareTwoDatesShouldEqual(newDateAfterDuration, date)) {
                    Player ownerMatchPlayer = playerService.returnOwnerMatchPlayer(match);
                    matchService.sendNotificationOfMatchEvaluation(match, ownerMatchPlayer);
                }
            }
        }
    }


}
