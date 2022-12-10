package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.mapper.GlobalStatsMapper;
import com.example.testpfsentities.mapper.MatchMapper;
import com.example.testpfsentities.repository.MatchRepository;
import com.example.testpfsentities.service.*;
import com.example.testpfsentities.utils.StringUtils;
import com.example.testpfsentities.validations.MatchValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
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
    public Match findMatchById(String match_id) {
        Optional<Match> optionalMatch = matchRepository.findById(match_id);
        if (optionalMatch.isEmpty()) {
            throw new IllegalArgumentException("match not found !");
        }
        return optionalMatch.get();
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
        TeamDto teamDto = matchDto.getTeams().get(0);
        String match_id = matchDto.getId();
        Match match = this.findMatchById(match_id);
        Team team = teamService.createTeam(teamDto);
        teamService.save(team);
        Match match1 = this.setTeams(match, team);
        matchRepository.save(match1);
    }

    @Override
    public void deleteMatch(MatchDto matchDto) {

    }

    @Override
    public List<MatchDto> getMatchesGround(String ground_id) {
        return null;
    }


}
