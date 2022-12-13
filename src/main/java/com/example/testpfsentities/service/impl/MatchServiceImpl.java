package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.*;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.mapper.MatchMapper;
import com.example.testpfsentities.mapper.TeamMapper;
import com.example.testpfsentities.mapper.TeamPlayerMapper;
import com.example.testpfsentities.repository.MatchRepository;
import com.example.testpfsentities.service.*;
import com.example.testpfsentities.utils.StringUtils;
import com.example.testpfsentities.validations.MatchValidator;
import com.example.testpfsentities.validations.TeamValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchServiceImpl implements MatchService {
    private final TeamMapper teamMapper;
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final TeamPlayerMapper teamPlayerMapper;
    private final MatchValidator matchValidator;
    private final TeamValidator teamValidator;
    private final GroundService groundService;
    private final ReservationService reservationService;
    private final NotificationOwnerService notificationOwnerService;
    private final TeamService teamService;
    private final NotificationPlayerService notificationPlayerService;

    @Override
    public void createMatch(MatchDto matchDto) {
        matchValidator.validateCreation(matchDto);
        String ground_name = matchDto.getGroundName();
        Ground ground = groundService.findByName(ground_name);
        Match match = matchMapper.toBo(matchDto);
        Reservation reservation = reservationService.create(matchDto, ground);

        if (matchDto.getPrivateMatch()) {
            match.setMatchCode(StringUtils.getRandomNumberString());
        }

        match.setGround(ground);
        Match match1 = matchRepository.save(match);
        teamService.assignPlayersWithTeams(match1.getTeams(), matchDto.getTeams().get(0));

        NotificationOwner notificationOwner = notificationOwnerService.create(reservation, ground);
        notificationOwnerService.save(notificationOwner);

    }


    @Override
    public List<MatchDto> getMatches() {
        return matchRepository.findAll().stream().map(matchMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void evaluateMatch(Match match) {

        String match_id=match.getId();

        List<Team> teams = match.getTeams();
        TeamPlayer teamPlayer=teams.get(0).getPlayersTeams().get(0);
        log.info("here asjaskd");
        Player p=teamPlayer.getPlayer();
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
        return matchRepository.findByDate(date);
    }

    @Override
    public List<MatchDto> getMatchsByRegionAndTown(MatchSearchDto matchSearchDto) {
        return matchMapper.toDto(matchRepository.findMatchsByRegionAndTown(
                matchSearchDto.getTown(),
                matchSearchDto.getRegion()
        ));
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
    public Match joinMatchAsTeam(MatchDto matchDto) {
        Match match = this.findMatchById(matchDto.getId());
        teamValidator.validateInsertionTeam(matchDto, match);
        match.getTeams().addAll(teamMapper.toBo(matchDto.getTeams()));
        Match matchSaved = matchRepository.save(match);
        TeamDto teamDto = matchDto.getTeams().get(0);
        teamService.assignPlayersWithTeams(matchSaved.getTeams(), teamDto);
        return match;
        // this part is for notification
        // we should notify the owner match player that a new team has joined the game
    }


    @Override
    public Match joinMatchAsPlayer(MatchDto matchDto) {
        Match match = this.findMatchById(matchDto.getId());
        List<TeamPlayerDto> teamPlayerDtoList = matchDto.getTeams().get(0).getPlayersTeams();

        var team = match.getTeams().stream()
                .filter(teamed -> teamed.getName().equals(matchDto.getTeams().get(0).getName()))
                .toList().get(0);

        var list = team.getPlayersTeams();

        // this is for verification of inserting the player inside the match
        teamService.validateInsertionPlayer(team, matchDto.getTeams().get(0).getPlayersTeams());

        list.addAll(teamPlayerMapper.toBo(teamPlayerDtoList));
        TeamDto teamDto = matchDto.getTeams().get(0);
        teamService.assignPlayersWithTeams(match.getTeams(), teamDto);
        return matchRepository.save(match);

    }

    @Override
    public void deleteMatch(MatchDto matchDto) {
        matchRepository.delete(matchMapper.toBo(matchDto));
    }

    @Override
    public Integer countNumberTeams(String match_id) {
        Optional<Match> optionalMatch = matchRepository.findById(match_id);
        if (optionalMatch.isEmpty()) {
            throw new IllegalArgumentException("match not found !");
        }
        Match match = optionalMatch.get();
        return match.getTeams().size();
    }

    @Override
    public List<MatchDto> getMatchesGround(String ground_name) {
        Ground ground = groundService.findByName(ground_name);
        return ground.getMatches().stream().map(matchMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Integer getNumberOwnerMatches(String owner_id) {
        List<Ground> grounds = groundService.getOwnerGroundsBo(owner_id);
        grounds.forEach(ground -> log.info(ground.getName()));
        return grounds.stream().mapToInt(ground -> getMatchesGround(ground.getName()).size()).sum();
    }

    @Override
    public List<MatchDto> getMatchesOfOwnerGrounds(MatchDto matchDto) {
        var listMatches = matchRepository.findAll();
        List<Match> finalListMatches = new ArrayList<>();
        for (Match match : listMatches) {
            if (match.getGround().getOwner().getId().equals(matchDto.getOwnerDto().getId())) {
                finalListMatches.add(match);
            }
        }
        return matchMapper.toDto(finalListMatches);
    }

    @Override
    public String getMatchCode(String match_id) {
        Match match = findMatchById(match_id);
        if (!match.getPrivateMatch()) {
            throw new IllegalArgumentException("Sorry this match is public ! you don't need a code ");
        }
        return match.getMatchCode();
    }


}
