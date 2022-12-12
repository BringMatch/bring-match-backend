package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.GlobalStatsDto;
import com.example.testpfsentities.service.GlobalStatsService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.GlobalStats)
@RequiredArgsConstructor
public class GlobalStatsController {

    private final GlobalStatsService globalStatsService;

    @PostMapping(ApiPaths.Save_GlobalStats)
    public void save(@RequestBody @Validated GlobalStatsDto globalStatsDto){globalStatsService.saveStats(globalStatsDto);}
}
