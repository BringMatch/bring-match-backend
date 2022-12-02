package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.PLAYERS)
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostConstruct
    public void initPlayer() {
        playerService.createPlayer();
    }

//    @GetMapping(ApiPaths.GET_PLAYERS)
//    public ResponseEntity<List<Player>> getPlayers(Authentication authentication, Principal) {
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .header("Custom-Header", "foo")
//                .header("Bearer " , "myJwtToken")
//                .body(playerService.getPlayers());
//
//    }

    @GetMapping(ApiPaths.GET_PLAYERS)
    public ResponseEntity<List<PlayerDto>> getPlayers() {
        return ResponseEntity.ok().body(playerService.getPlayers());
    }

    @PostMapping(ApiPaths.SAVE_PLAYER)
    public void savePlayer(@RequestBody @Validated PlayerDto playerDto) {
        playerService.save(playerDto);
    }

    @PostMapping(ApiPaths.CREATE_TEAM)
    public void createTeam(@RequestBody @Validated TeamDto teamDto) {
        playerService.createTeam(teamDto);
    }

    @PostMapping(ApiPaths.CREATE_MATCH)
    public void createMatch(@RequestBody @Validated MatchDto matchDto) {
        playerService.createMatch(matchDto);
    }

    @PutMapping(ApiPaths.JOIN_MATCH_AS_PLAYER)
    public void joinMatchAsPlayer(@RequestBody @Validated TeamDto teamDto) {
        playerService.joinMatchAsPlayer(teamDto);
    }

    @PutMapping(ApiPaths.JOIN_MATCH_AS_TEAM)
    public void joinMatchAsTeam(@RequestBody @Validated PlayerDto playerDto) {
        playerService.joinMatchAsTeam(playerDto);
    }

    @GetMapping(ApiPaths.GET_MATCHES)
    public ResponseEntity<List<MatchDto>> getMatches() {
        return ResponseEntity.ok().body(playerService.getMatches());
    }

    @PostMapping(ApiPaths.EVALUATE_MATCH)
    public void evaluateMatch(@RequestBody @Validated MatchDto matchDto) {
        playerService.evaluateMatch(matchDto);
    }


}
