package com.example.testpfsentities.controller;

import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.repository.PlayerRepository;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(ApiPaths.GET_PLAYERS)
    public ResponseEntity<List<Player>> getPlayers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(playerService.getPlayers());
    }
}
