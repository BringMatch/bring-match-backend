package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.mapper.MatchMapper;
import com.example.testpfsentities.mapper.PlayerMapper;
import com.example.testpfsentities.mapper.TeamMapper;
import com.example.testpfsentities.repository.*;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.service.ReservationRepository;
import com.example.testpfsentities.utils.SecurityUtils;
import com.example.testpfsentities.utils.StringUtils;
import com.example.testpfsentities.validations.MatchValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final PlayerMapper playerMapper;
    private final MatchValidator matchValidator;
    private final MatchMapper matchMapper;
    private final TeamMapper teamMapper;
    private final MatchRepository matchRepository;
    private final GroundRepository groundRepository;
    private final ReservationRepository reservationRepository;
    private final NotificationOwnerRepository notificationOwnerRepository;

    @Override
    public void createPlayer() {
        Player player = new Player();
        player.setEmail("yessinejawa@gmail.com");
        player.setUpdatedAt(LocalDateTime.now());
        player.setPassword("yessine");
        player.setFirstName("ajaoua");
        player.setLastName("ajaqsdfoua");
        player.setPhoneNumber("45454");
        player.setRoleName("player");
        player.setCreatedAt(LocalDateTime.now());
        player.setMatch_owner(true);
//        player.setTeams(null);
        player.setNotificationPlayer(null);
        playerRepository.save(player);
    }

    @Override
    public List<PlayerDto> getPlayers() {
        return playerRepository.findAll().stream().map(playerMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void save(PlayerDto playerDto) {
        playerRepository.save(playerMapper.toBo(playerDto));
    }

    @Override
    public void createTeam(TeamDto teamDto) {

    }

    @Override
    public void createMatch(MatchDto matchDto) {

        matchValidator.validateCreation(matchDto);

        String ground_name = matchDto.getGroundName();
        Optional<Ground> groundOptional = groundRepository.findByName(ground_name);
        if (groundOptional.isEmpty()) {
            throw new IllegalArgumentException("ground not existing !");
        }

        Ground ground = groundOptional.get();
        Match match = matchMapper.toBo(matchDto);

        Reservation reservation = new Reservation();
        reservation.setTeam_one_creator_id(matchDto.getTeams().get(0).getPlayers().get(0));
        reservation.setTeam_two_creator_id(null);
        reservation.setStatus(false);
        reservation.setGround(ground);

        List<TeamDto> teamDtoList = matchDto.getTeams();


        teamDtoList.forEach((teamDto) -> {
            List<Player> players = new ArrayList<>();
            Team team = teamMapper.toBo(teamDto);
            teamDto.getPlayers()
                    .forEach(player_id -> {
                        var player = playerRepository.findById(player_id).get();
                        players.add(player);
                    });
            team.setPlayers(players);
            teamRepository.save(team);
        });

        if (matchDto.getPrivateMatch() == true) {
            match.setMatchCode(StringUtils.getRandomNumberString());
        }
        match.setGround(ground);
        matchRepository.save(match);
        reservationRepository.save(reservation);

        NotificationOwner notificationOwner = new NotificationOwner();
        notificationOwner.setCreatedAt(LocalDateTime.now());
        notificationOwner.setReservation(reservation);
        notificationOwner.setOwner(ground.getOwner());
        notificationOwnerRepository.save(notificationOwner);

    }

    @Override
    public void joinMatchAsPlayer(PlayerDto playerDto) {

    }

    @Override
    public void joinMatchAsTeam(TeamDto teamDto) {
        Team team = teamMapper.toBo(teamDto);
    }

    @Override
    public List<MatchDto> getMatches() {
        return matchRepository.findAll().stream().map(matchMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void evaluateMatch(MatchDto matchDto) {

    }

}
