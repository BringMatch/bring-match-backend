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
@RequestMapping(ApiPaths.GLOBAL_STATS)
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class GlobalStatsController {
    private final GlobalStatsService globalStatsService;

    @PostMapping(ApiPaths.SAVE_GLOBAL_STATS + "/{notification_player_id}")
    public void save(@RequestBody @Validated GlobalStatsDto globalStatsDto,
                     @PathVariable(name = "notification_player_id") String notification_player_id) {
        globalStatsService.saveStats(globalStatsDto, notification_player_id);
    }

    @GetMapping(ApiPaths.GET_GLOBAL_STATS)
    public ResponseEntity<List<GlobalStatsDto>> getGlobalStats() {
        return ResponseEntity.ok().body(globalStatsService.getAllGlobalStats());
    }
}
