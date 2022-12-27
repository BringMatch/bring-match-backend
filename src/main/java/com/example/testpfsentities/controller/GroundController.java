package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.GroundSearchDto;
import com.example.testpfsentities.service.GroundService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.GROUNDS)
@RequiredArgsConstructor
@Slf4j
public class GroundController {
    private final GroundService groundService;

    @GetMapping(ApiPaths.GET_GROUNDS)
    public ResponseEntity<List<GroundDto>> getGrounds() {
        return ResponseEntity.ok().body(groundService.getGrounds());
    }

    @PutMapping(ApiPaths.UPDATE_GROUND)
    public void updateGround(@RequestBody @Validated GroundDto groundDto) {
        groundService.updateGround(groundDto);
    }

    @PostMapping(ApiPaths.SAVE_GROUND)
    public void save(@RequestBody @Validated GroundDto groundDto) {
        groundService.saveGround(groundDto);
    }

    @GetMapping
    public ResponseEntity<List<GroundDto>> getAllGrounds() {
        return ResponseEntity.ok().body(groundService.getOwnerGrounds());
    }

    @PutMapping(ApiPaths.UPDATE_STATUS_GROUND)
    public void updateStatusGround(@RequestBody @Validated GroundDto groundDto) {
        groundService.updateStatusGround(groundDto);
    }

    @DeleteMapping(ApiPaths.DELETE_GROUND + "/{ground_id}")
    public void deleteGround(@PathVariable(name = "ground_id") String ground_id) {
        groundService.deleteGround(ground_id);
    }

    @GetMapping(ApiPaths.GET_NUMBER_OWNER_GROUNDS)
    public ResponseEntity<Integer> getNumberOwnerGrounds() {
        return ResponseEntity.ok().body(groundService.getNumberOwnerGrounds());
    }

    @GetMapping(ApiPaths.GET_NUMBER_OWNER_GROUNDS_OPEN)
    public ResponseEntity<Integer> getNumberOwnerGroundsOpen() {
        return ResponseEntity.ok().body(groundService.getNumberGroundsOpen());
    }

    @GetMapping(ApiPaths.GET_NUMBER_OWNER_GROUNDS_CLOSED)
    public ResponseEntity<Integer> getNumberOwnerGroundsClosed() {
        return ResponseEntity.ok().body(groundService.getNumberGroundsClosed());
    }

    @GetMapping(ApiPaths.SEARCH_GROUND)
    public ResponseEntity<List<GroundDto>> getGroundsByRegionAndTown(
            @RequestParam(value = "town", required = false) String town,
            @RequestParam(value = "groundName", required = false) String name
    ) {
        return ResponseEntity.ok().body(groundService.getAllGroundsByTownAndRegion(new GroundSearchDto(town,name)));
    }

    @GetMapping(ApiPaths.GET_OPEN_AND_FREE_GROUNDS)
    public ResponseEntity<List<GroundDto>> getOpenAndFreeGrounds(){
        return ResponseEntity.ok().body(groundService.getOpenAndFreeGrounds());
    }

}
