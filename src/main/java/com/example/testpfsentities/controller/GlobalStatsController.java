package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.GlobalStatsDto;
import com.example.testpfsentities.service.GlobalStatsService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.GlobalStats)
@RequiredArgsConstructor
public class GlobalStatsController {

    private final GlobalStatsService globalStatsService;

    @PostMapping(ApiPaths.Save_GlobalStats + "/{notification_player_id}")
    public void save(@RequestBody @Validated GlobalStatsDto globalStatsDto,
                     @PathVariable(name = "notification_player_id") String notification_player_id)
    {
        globalStatsService.saveStats(globalStatsDto,notification_player_id);
    }
    // I passed the notification player as an argument to make readable I mean set the boolean of read true
    // to never show it to the user again in his notifications !
    // And then we should pass the match_id to then give the ability to the owner to see this matches with details of course !

    @GetMapping(ApiPaths.GET_GLOBAL_STATS)
    public ResponseEntity<List<GlobalStatsDto>> getGlobalStats(){
        return ResponseEntity.ok().body(globalStatsService.getAllGlobalStats());
    }
}
