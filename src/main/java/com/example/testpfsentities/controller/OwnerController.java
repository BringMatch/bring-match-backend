package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.service.OwnerService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@Slf4j
@RequestMapping(ApiPaths.OWNERS)
@RestController
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping(ApiPaths.SAVE_OWNER)
    public void save(@RequestBody @Validated OwnerDto ownerDto) {
        ownerService.save(ownerDto);
    }

    @GetMapping(ApiPaths.GET_OWNERS)
    public ResponseEntity<List<OwnerDto>> getOwners() {
        return ResponseEntity.ok().body(ownerService.getOwners());
    }

    @PostMapping(ApiPaths.SAVE_GROUND)
    public void save(@RequestBody @Validated GroundDto groundDto) {
        ownerService.saveGround(groundDto);
    }

    @GetMapping(ApiPaths.GET_GROUNDS + "{owner_id}")
    public ResponseEntity<List<GroundDto>> getAllGrounds(@PathVariable(name = "owner_id") String owner_id) {
        return ResponseEntity.ok().body(ownerService.getGrounds(owner_id));
    }

    @GetMapping(ApiPaths.GET_MATCHES + "/{ground_id}")
    public ResponseEntity<List<MatchDto>> getMatchesGround(@PathVariable(name = "ground_id") String ground_id) {
        return ResponseEntity.ok().body(ownerService.getMatchesGround(ground_id));
    }

    @PutMapping(ApiPaths.UPDATE_GROUND)
    public void updateGround(@RequestBody @Validated GroundDto groundDto){
        ownerService.updateGround(groundDto);
    }

    @DeleteMapping(ApiPaths.DELETE_MATCH)
    public void deleteMatch(@RequestBody() MatchDto matchDto){
        ownerService.deleteMatch(matchDto);
    }

}
