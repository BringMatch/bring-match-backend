package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.MatchSearchDto;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.service.MatchService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.MATCHES)
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @PostMapping(ApiPaths.CREATE_MATCH)
    public void createMatch(@RequestBody @Validated MatchDto matchDto) {
        matchService.createMatch(matchDto);
    }

    @GetMapping("")
    public ResponseEntity<List<MatchDto>> getMatches() {
        return ResponseEntity.ok().body(matchService.getMatches());
    }

//    @PostMapping(ApiPaths.EVALUATE_MATCH +  "/match_id")
//    public void evaluateMatch(@PathVariable(name = "match_id") String match_id) {
//        matchService.evaluateMatch(match_id);
//    }

    @PostMapping(ApiPaths.JOIN_MATCH_AS_TEAM)
    public ResponseEntity<Match> joinMatchAsTeam(@RequestBody @Validated MatchDto matchDto) {
        return ResponseEntity.ok().body(matchService.joinMatchAsTeam(matchDto));
    }

    @PostMapping(ApiPaths.JOIN_MATCH_AS_PLAYER)
    public ResponseEntity<Match> joinMatchAsPlayer(@RequestBody @Validated MatchDto matchDto) {
        return ResponseEntity.ok().body(matchService.joinMatchAsPlayer(matchDto));
    }

    @DeleteMapping(ApiPaths.DELETE_MATCH)
    public void deleteMatch(@RequestBody() MatchDto matchDto) {
        matchService.deleteMatch(matchDto);
    }


    @GetMapping(ApiPaths.GET_CURRENT_NUMBER_TEAMS + "/{match_id}")
    public ResponseEntity<Integer> getCurrentNumberTeams(@PathVariable(name = "match_id") String match_id) {
        return ResponseEntity.ok().body(matchService.countNumberTeams(match_id));
    }

    @GetMapping(ApiPaths.GET_MATCHES_GROUND + "{ground_name}")
    public ResponseEntity<List<MatchDto>> getMatchesGround(@PathVariable(name = "ground_name") String ground_name) {
        return ResponseEntity.ok().body(matchService.getMatchesGround(ground_name));
    }

    @GetMapping(ApiPaths.GET_NUMBER_OWNER_MATCHES + "/{owner_id}")
    public ResponseEntity<Integer> getNumberOwnerMatches(@PathVariable(name = "owner_id") String owner_id) {
        return ResponseEntity.ok().body(matchService.getNumberOwnerMatches(owner_id));
    }

    @GetMapping(ApiPaths.SEARCH_MATCH)
    public ResponseEntity<List<MatchDto>> getGroundsByRegionAndTown(
            @RequestParam(value = "town", required = false) String town,
            @RequestParam(value = "region", required = false) String region,
            @RequestParam(value = "groundName", required = false) String groundName,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam(value = "privateMatch", required = false) boolean privateMatch
    ) {

            return ResponseEntity.ok().body(matchService.searchforMatches(new MatchSearchDto(town, region, date, groundName, privateMatch)));
    }


    @GetMapping(ApiPaths.SEARCH_MATCH_BY_DATE)
    public ResponseEntity<List<MatchDto>> getMatchByIDate(
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {

            return ResponseEntity.ok().body(matchService.getMatchByDate(date));

    }

    @GetMapping(ApiPaths.GET_MATCHES_OF_OWNER_GROUNDS)
    public ResponseEntity<List<MatchDto>> getMatchesOfOwnerGrounds(@RequestBody @Validated MatchDto matchDto) {
        return ResponseEntity.ok().body(matchService.getMatchesOfOwnerGrounds(matchDto));
    }

    @GetMapping(ApiPaths.GET_MATCH_CODE_IF_PRIVATE + "/{match_id}")
    public ResponseEntity<String> getMatchCodeIfPrivate(@PathVariable(name = "match_id") String match_id) {
        return ResponseEntity.ok().body(matchService.getMatchCode(match_id));
    }

    @GetMapping(ApiPaths.GET_MATCHES + "{match_id}")
    public ResponseEntity<MatchDto> getMatchById(@PathVariable(name = "match_id") String match_id) {
        return ResponseEntity.ok().body(matchService.getMatchById(match_id));
    }


}
