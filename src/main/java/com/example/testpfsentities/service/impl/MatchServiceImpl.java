package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.*;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.mapper.GlobalStatsMapper;
import com.example.testpfsentities.mapper.MatchMapper;
import com.example.testpfsentities.mapper.TeamMapper;
import com.example.testpfsentities.repository.MatchRepository;
import com.example.testpfsentities.service.*;
import com.example.testpfsentities.utils.StringUtils;
import com.example.testpfsentities.validations.MatchValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchServiceImpl implements MatchService {
    private final TeamMapper teamMapper;
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final MatchValidator matchValidator;
    private final GlobalStatsMapper globalStatsMapper;
    private final GroundService groundService;
    private final ReservationService reservationService;
    private final NotificationOwnerService notificationOwnerService;
    private final GlobalStatsService globalStatsService;
    private final TeamService teamService;
    private final TeamPlayerService teamPlayerService;
    private final NotificationPlayerService notificationPlayerService;

    @Override
    public void createMatch(MatchDto matchDto) {
        matchValidator.validateCreation(matchDto);
        String ground_name = matchDto.getGroundName();
        Ground ground = groundService.findByName(ground_name);
        Match match = matchMapper.toBo(matchDto);
        Reservation reservation = reservationService.create(matchDto, ground);

        if (matchDto.getPrivateMatch() == true) {
            match.setMatchCode(StringUtils.getRandomNumberString());
        }

        match.setGround(ground);
        Match match1 = matchRepository.save(match);

        teamService.assignPlayersWithTeams(match1.getTeams());

        NotificationOwner notificationOwner = notificationOwnerService.create(reservation, ground);
        notificationOwnerService.save(notificationOwner);

    }


    @Override
    public List<Match> getMatches() {
       return matchRepository.findAll();
    }

    @Override
    public void evaluateMatch(Match match) {
        Optional<Match> optionalMatch = matchRepository.findById(match.getId());
        if (optionalMatch.isEmpty()){
            throw new IllegalArgumentException("match not found");
        }
        String match_id=match.getId();

        Player p=match.getTeams().get(0).getPlayersTeams().get(0).getPlayer();
//        TeamPlayer teamPlayer=teamPlayerService.getTeamPlayer();

//        Player palyer=teamPlayer.getPlayer();
        NotificationPlayer notificationPlayer=notificationPlayerService.create(match_id,p);
        notificationPlayerService.save(notificationPlayer);

    }

    @Override
    public Match findMatchById(MatchDto matchDto) {
        Optional<Match> optionalMatch = matchRepository.findById(matchDto.getId());
        if (optionalMatch.isEmpty()) {
            throw new IllegalArgumentException("match not found !");
        }
        return matchMapper.toBo(matchDto);
    }

    @Override
    public List<Match> getMatchByDate(Date date) {

        return  matchRepository.findByDate(date);
    }

    @Override
    public void save(Match match) {
        matchRepository.save(match);
    }

    @Override
    public Match setTeams(Match match, Team team) {
        List<Team> teamList = match.getTeams();
        teamList.add(team);
        match.setTeams(teamList);
        return match;
    }
    @Override
    public void joinMatchAsTeam(MatchDto matchDto) {
        Match match = this.findMatchById(matchDto);
        match.getTeams().addAll(teamMapper.toBo(matchDto.getTeams()));
        Match matchSaved = matchRepository.save(match);

        teamService.assignPlayersWithTeams(matchSaved.getTeams());
//        notificationPlayerService.create();
//        NotificationPlayer notificationPlayer = new NotificationPlayer();
//        notificationPlayer.setCurrentMatchId(matchSaved.getId());
//        notificationPlayer.set
//        notificationPlayer.setOwner_match();
    }

    @Override
    public void joinMatchAsPlayer(MatchDto matchDto) {
        Match match = this.findMatchById(matchDto);
        Team team = teamService.getTeamById(matchDto);
        teamService.assignNewPlayerToTeam(team, matchDto.getTeams().get(0).getPlayersTeams());
        Match matchSaved = matchRepository.save(match);
        teamService.assignPlayersWithTeams(matchSaved.getTeams());
    }

    @Override
    public void deleteMatch(MatchDto matchDto) {

    }

    @Override
    public List<MatchDto> getMatchesGround(String ground_name) {
        return matchRepository.findByGroundName(ground_name);
    }

    @Override
    public Integer countNumberTeams(String match_id) {
        return 0;
    }


}
