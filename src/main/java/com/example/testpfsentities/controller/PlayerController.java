package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.PlayerSearchDto;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.PLAYERS)
@CrossOrigin(origins = "http://localhost:8081")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
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

    @GetMapping(ApiPaths.SEARCH_PLAYERS)
    public ResponseEntity<List<PlayerDto>> getPlayersByIdAndNameAndTownAndRegion(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "town", required = false) String town,
            @RequestParam(value = "region", required = false) String region
    ) {
        return ResponseEntity.ok().body(playerService.getPlayers(new PlayerSearchDto(firstName, lastName, town, region)));
    }


    @GetMapping(ApiPaths.GET_GOALS_SCORED)
    public ResponseEntity<Integer> getGoalsScored(){
        return ResponseEntity.ok().body(playerService.getGoalsScored());
    }

}
