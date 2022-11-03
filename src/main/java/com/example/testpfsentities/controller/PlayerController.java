package com.example.testpfsentities.controller;

import com.example.testpfsentities.repository.PlayerRepository;
import com.example.testpfsentities.service.PlayerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostConstruct
    public void initPlayer(){
        playerService.createPlayer();
    }
}
