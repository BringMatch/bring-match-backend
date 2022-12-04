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

    @PostMapping(ApiPaths.JOIN_MATCH_AS_PLAYER)
    public void joinMatchAsPlayer(@RequestBody @Validated PlayerDto playerDto) {
        playerService.joinMatchAsPlayer(playerDto);
    }

}
