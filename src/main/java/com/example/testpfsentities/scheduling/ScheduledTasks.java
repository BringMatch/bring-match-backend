package com.example.testpfsentities.scheduling;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.repository.MatchRepository;
import com.example.testpfsentities.service.MatchService;
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
    private final MatchService matchService;
    private final MatchRepository matchRepository;


    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

    public void insertingRecordNotification(){

    }
    @Scheduled(cron = "* * * * * *")
    public void EvaluateMatchAfterFourHour() throws Exception{
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Create a LocalDate object that represents the current date minus four hours
        LocalDate currentDatePlusFourHours = currentDateTime.minusHours(4).toLocalDate();
        //convert from LocalDate to LocalDateTime
       // LocalDateTime dateTime=currentDatePlusFourHours.atStartOfDay();
       Date newDate = new Date(System.currentTimeMillis() - 3600 * 4000);
        try {
            List<Match> matches=matchRepository.findAll();
            for (Match match :matches){
                if (match.getDate().getHours() == newDate.getHours()){
                    matchService.evaluateMatch(match);
                }

                log.info("done");

            }
        }catch (Exception e){
            System.out.println("no match has selected");
        }



    }
}
