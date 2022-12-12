package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.service.GroundService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping(ApiPaths.GET_GROUNDS + "{owner_id}")
    public ResponseEntity<List<GroundDto>> getAllGrounds(@PathVariable(name = "owner_id") String owner_id) {
        log.info(groundService.getOwnerGrounds(owner_id).toString());
        return ResponseEntity.ok().body(groundService.getOwnerGrounds(owner_id));
    }

    @PutMapping(ApiPaths.UPDATE_STATUS_GROUND)
    public void updateStatusGround(@RequestBody @Validated GroundDto groundDto) {
        groundService.updateStatusGround(groundDto);
    }

    @DeleteMapping(ApiPaths.DELETE_GROUND + "/{ground_id}")
    public void deleteGround(@PathVariable(name = "ground_id") String ground_id) {
        groundService.deleteGround(ground_id);
    }

    @GetMapping(ApiPaths.GET_NUMBER_OWNER_GROUNDS + "/{owner_id}")
    public ResponseEntity<Integer> getNumberOwnerGrounds(@PathVariable(name = "owner_id") String owner_id) {
        return ResponseEntity.ok().body(groundService.getNumberOwnerGrounds(owner_id));
    }

    @GetMapping(ApiPaths.GET_NUMBER_OWNER_GROUNDS_OPEN + "/{owner_id}")
    public ResponseEntity<Integer> getNumberOwnerGroundsOpen(@PathVariable(name = "owner_id") String owner_id) {
        return ResponseEntity.ok().body(groundService.getNumberGroundsOpen(owner_id));
    }

    @GetMapping(ApiPaths.GET_NUMBER_OWNER_GROUNDS_CLOSED + "/{owner_id}")
    public ResponseEntity<Integer> getNumberOwnerGroundsClosed(@PathVariable(name = "owner_id") String owner_id) {
        return ResponseEntity.ok().body(groundService.getNumberGroundsClosed(owner_id));
    }


}
