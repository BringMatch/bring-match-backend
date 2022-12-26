package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.*;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.entities.enums.MatchResult;
import com.example.testpfsentities.entities.enums.MatchStatus;
import com.example.testpfsentities.mapper.*;
import com.example.testpfsentities.repository.GlobalStatsRepository;
import com.example.testpfsentities.repository.MatchRepository;
import com.example.testpfsentities.repository.PlayerRepository;
import com.example.testpfsentities.repository.PlayerStatsRepository;
import com.example.testpfsentities.service.*;
import com.example.testpfsentities.utils.StringUtils;
import com.example.testpfsentities.validations.MatchValidator;
import com.example.testpfsentities.validations.TeamValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchServiceImpl implements MatchService {
    private final PlayerStatsRepository playerStatsRepository;
    private final GlobalStatsRepository globalStatsRepository;
    private final GlobalStatsMapper globalStatsMapper;
    private final TeamMapper teamMapper;
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final MatchValidator matchValidator;
    private final TeamValidator teamValidator;
    private final GroundService groundService;
    private final ReservationService reservationService;
    private final NotificationOwnerService notificationOwnerService;
    private final TeamService teamService;
    private final NotificationPlayerService notificationPlayerService;
    private final PlayerStatsService playerStatsService;
    private final PlayerStatsMapper playerStatsMapper;


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
        ground.setFree(false);
        match.setMatchStatus(MatchStatus.NOT_PLAYED);
        Match match1 = matchRepository.save(match);

        teamService.assignPlayersWithTeams(match1.getTeams(), matchDto.getTeams().get(0));
        teamService.assignLengthTeamWithMatchLength(match1.getTeams(), matchDto.getNumberTeamPlayers());
        playerStatsService.savePlayerStats();

        NotificationOwner notificationOwner = notificationOwnerService.create(reservation, ground);
        notificationOwnerService.save(notificationOwner);

    }


    @Override
    public List<MatchDto> getMatches() {
        return matchRepository.findAll().stream().map(matchMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void evaluateMatch(EvaluationMatchDto evaluationMatchDto) {

        var globalStatsDto = evaluationMatchDto.getGlobalStatsDto();
        GlobalStats globalStats = globalStatsMapper.toBo(globalStatsDto);
        globalStatsRepository.save(globalStats);
        var listPlayerStats = evaluationMatchDto.getPlayers();
        playerStatsRepository.saveAll(playerStatsMapper.toBo(listPlayerStats));

//        var match = findMatchById(evaluationMatchDto.getGlobalStatsDto().getMatch().getId());
//        playerStatsService.updateGoalsScoredWhenMatchEnds(match , listPlayerStats);
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
    public List<MatchDto> getMatchByDate(Date date) {
        Date currentDate = new Date(System.currentTimeMillis() - 3600 * 1000);

        List<Match> matches;

        if (date == null) {
            matches = matchRepository.findAllBycurrentDate(currentDate);

        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, 1);
            Date modifiedDate = calendar.getTime();
            matches = matchRepository.findByDate(currentDate, modifiedDate);
        }
        return matchMapper.toDto(matches);
    }

    @Override
    public List<MatchDto> searchforMatches(MatchSearchDto matchSearchDto) {

        Date currentDate = new Date(System.currentTimeMillis() - 3600 * 1000);
        List<Match> matches;

        if (matchSearchDto.getDate() == null) {
            matches = matchRepository.findAllBycurrentDate(currentDate);

        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(matchSearchDto.getDate());
            calendar.add(Calendar.DATE, 1);
            Date modifiedDate = calendar.getTime();
            matches = matchRepository.findByDate(currentDate, modifiedDate);
        }

        var grounds = groundService.getAllGroundsByTownAndRegion(new GroundSearchDto(matchSearchDto.getTown(), matchSearchDto.getGround_name()));
        List<Match> matchDtoList = new ArrayList<>();
        for (Match match : matches) {
            for (GroundDto ground : grounds) {
                if (ground.getId().equals(match.getGround().getId())) {
                    matchDtoList.add(match);
                }
            }
        }
        return matchMapper.toDto(matchDtoList);
    }

    @Override
    public void save(Match match) {
        matchRepository.save(match);
    }

    @Override
    public Match joinMatchAsTeam(MatchDto matchDto) {
        Match match = this.findMatchById(matchDto.getId());
        teamValidator.validateInsertionTeam(matchDto, match);
        List<Team> teams = teamMapper.toBo(matchDto.getTeams());
        match.getTeams().addAll(teams);
        Match matchSaved = matchRepository.save(match);
        TeamDto teamDto = matchDto.getTeams().get(0);
        teamService.assignPlayersWithTeams(matchSaved.getTeams(), teamDto);
        teamService.setLengthTeamWithMaxLengthMatchWhenJoinAsTeam(match, matchDto);
        playerStatsService.savePlayerStats();

        // this part is for notification
        // we should notify the owner match player that a new team has joined the game

//        notificationPlayerService.create(matchDto);
        return match;

    }


    @Override
    public Match joinMatchAsPlayer(MatchDto matchDto) {
        Match match = this.findMatchById(matchDto.getId());
        List<TeamPlayerDto> teamPlayerDtoList = matchDto.getTeams().get(0).getPlayersTeams();

        var team = match.getTeams().stream()
                .filter(teamed -> teamed.getName().equals(matchDto.getTeams().get(0).getName()))
                .collect(Collectors.toList()).get(0);


        // this is for verification of inserting the player inside the match
        teamService.validateInsertionPlayer(team, matchDto.getTeams().get(0).getPlayersTeams());

        int current_length_team = team.getLength();
        team.setLength(current_length_team - 1);
        TeamDto teamDto = matchDto.getTeams().get(0);
        teamService.assignPlayersWithTeams(match.getTeams(), teamDto);
        playerStatsService.savePlayerStats();
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
//            if (match.getGround().getOwner().getId().equals(matchDto.getOwner().getId())) {
//                finalListMatches.add(match);
//            }
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

    @Override
    public MatchDto getMatchById(String match_id) {
        var match = findMatchById(match_id);
        return matchMapper.toDto(match);
    }

    @Override
    public Integer getLengthTeamInMatch(String match_id) {
        Match match = findMatchById(match_id);
        return match.getNumberTeamPlayers();
    }

    @Override
    public List<Match> getPendingMatches() {
        return matchRepository.findAllByMatchStatus_Pending();
    }

    @Override
    public Integer getNumberMatchesOfPlayer(String player_id) {
        var matches = matchRepository.findAllByMatchStatus_Played();
        int result = 0;
        for (Match match : matches) {
            for (Team team : match.getTeams()) {
                for (TeamPlayer teamPlayer : team.getPlayersTeams()) {
                    if (teamPlayer.getPlayer().getId().equals(player_id)) {
                        result++;
                        break;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Integer getNumberMatchesWinOfPlayer(String player_id) {
        var matches = matchRepository.findAllByMatchStatus_Played();
        int result = 0;
        for (Match match : matches) {
            for (Team team : match.getTeams()) {
                if (team.getMatchResult().equals(MatchResult.WIN)) {
                    for (TeamPlayer teamPlayer : team.getPlayersTeams()) {
                        if (teamPlayer.getPlayer().getId().equals(player_id)) {
                            result++;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Integer getNumberMatchesLoseOfPlayer(String player_id) {
        var matches = matchRepository.findAllByMatchStatus_Played();
        int result = 0;
        for (Match match : matches) {
            for (Team team : match.getTeams()) {
                if (team.getMatchResult().equals(MatchResult.LOSE)) {
                    for (TeamPlayer teamPlayer : team.getPlayersTeams()) {
                        if (teamPlayer.getPlayer().getId().equals(player_id)) {
                            result++;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Integer getNumberMatchesDrawOfPlayer(String player_id) {
        var matches = matchRepository.findAllByMatchStatus_Played();
        int result = 0;
        for (Match match : matches) {
            for (Team team : match.getTeams()) {
                if (team.getMatchResult().equals(MatchResult.DRAW)) {
                    for (TeamPlayer teamPlayer : team.getPlayersTeams()) {
                        if (teamPlayer.getPlayer().getId().equals(player_id)) {
                            result++;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<MatchDto> getMatchesByPlayer(String player_id) {
        var matches = matchRepository.findAllByMatchStatus_Played();
        List<Match> finalFilteredListMatches = new ArrayList<>();
        var matchesDto = matchMapper.toDto(matches);
        for (MatchDto matchDto : matchesDto) {
            Match match = findMatchById(matchDto.getId());
            matchDto.setMatchResult(returnMatchResul(match, player_id));
        }
        return matchesDto;
    }

    @Override
    public void sendNotificationOfMatchEvaluation(Match match, Player player) {
        NotificationPlayer notificationPlayer = notificationPlayerService.create(match, player);
        notificationPlayerService.save(notificationPlayer);
    }

    private MatchResult returnMatchResul(Match match, String player_id) {
        for (Team team : match.getTeams()) {
            for (TeamPlayer teamPlayer : team.getPlayersTeams()) {
                if (teamPlayer.getPlayer().getId().equals(player_id)) {
                    return team.getMatchResult();
                }
            }
        }
        return null;
    }


}
