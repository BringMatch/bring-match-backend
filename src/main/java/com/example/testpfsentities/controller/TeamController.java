package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.service.TeamService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.TEAMS)
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
public class TeamController {
    private final TeamService teamService;

    @PostMapping(ApiPaths.CREATE_TEAM)
    public void createTeam(@RequestBody @Validated TeamDto teamDto) {
        teamService.createTeam(teamDto);
    }

    @GetMapping(ApiPaths.GET_FREE_POSITIONS_IN_A_TEAM + "/{team_id}")
    public ResponseEntity<List<String>> getFreePositions(@PathVariable(name = "team_id") String team_id) {
        return ResponseEntity.ok().body(teamService.getFreePositions(team_id));
    }
    @GetMapping(ApiPaths.GET_LENGTH_REMAINING + "/{team_name}")
    public ResponseEntity<Integer> getLengthRemaining(@PathVariable(name = "team_name") String team_name){
        return ResponseEntity.ok().body(teamService.getLengthRemaining(team_name));
    }

}
