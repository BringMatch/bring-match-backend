package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.service.TeamService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.TEAMS)
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    @PostMapping(ApiPaths.CREATE_TEAM)
    public void createTeam(@RequestBody @Validated TeamDto teamDto) {
        teamService.createTeam(teamDto);
    }

}
