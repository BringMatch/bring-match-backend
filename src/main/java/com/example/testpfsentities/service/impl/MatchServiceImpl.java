package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.mapper.MatchMapper;
import com.example.testpfsentities.repository.MatchRepository;
import com.example.testpfsentities.service.*;
import com.example.testpfsentities.utils.StringUtils;
import com.example.testpfsentities.validations.MatchValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final MatchValidator matchValidator;
    private final GroundService groundService;
    private final ReservationService reservationService;
    private final NotificationOwnerService notificationOwnerService;
    private final TeamService teamService;
//    private final NotificationPlayerService notificationPlayerService;

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
        //return matchRepository.findAll().stream().map(matchMapper::toDto).collect(Collectors.toList());
        return matchRepository.findAll();
    }

    @Override
    public void evaluateMatch(MatchDto matchDto) {

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
        Match match = findMatchById(matchDto);
        Match matchSaved = matchRepository.save(match);
        matchSaved.getTeams().forEach(team -> log.info("hahahahhaa"));
        teamService.assignPlayersWithTeams(matchSaved.getTeams());

//        notificationPlayerService.create();
//        NotificationPlayer notificationPlayer = new NotificationPlayer();
//        notificationPlayer.setCurrentMatchId(matchSaved.getId());
//        notificationPlayer.set
//        notificationPlayer.setOwner_match();
    }

    @Override
    public void joinMatchAsPlayer(MatchDto matchDto) {
        Match match = findMatchById(matchDto);
        Match matchSaved = matchRepository.save(match);
        teamService.assignPlayersWithTeams(matchSaved.getTeams());
    }

    @Override
    public void deleteMatch(MatchDto matchDto) {

    }

    @Override
    public List<MatchDto> getMatchesGround(String ground_id) {
        return null;
    }


}
