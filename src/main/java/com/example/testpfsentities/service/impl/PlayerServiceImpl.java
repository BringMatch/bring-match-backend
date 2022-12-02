package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.mapper.MatchMapper;
import com.example.testpfsentities.mapper.PlayerMapper;
import com.example.testpfsentities.repository.*;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.service.ReservationRepository;
import com.example.testpfsentities.validations.MatchValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        player.setTeams(null);
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


        /**
         this is for creating the reservation !
         */

        Reservation reservation = new Reservation();
        reservation.setTeam_one_creator_id(matchDto.getCreator_id());
        reservation.setTeam_two_creator_id(null);
        reservation.setStatus(false);
        reservation.setGround(ground);


        /**
         this is for creating the player !
         */

        Optional<Player> playerOptional = playerRepository.findById(matchDto.getCreator_id());
        if (groundOptional.isEmpty()) {
            throw new IllegalArgumentException("player not existing !");
        }

        Player player = playerOptional.get();
        player.setMatch_owner(true);

//        Team team = new Team();
//        team.setMatch(match);
//
//        List<Player> playerList = new ArrayList<>();
//        playerList.add(player);
//        team.setPlayers(playerList);
//        team.setName(matchDto.getTeamName());
//        team.setCreatedAt(LocalDateTime.now());


//        List<Team> listTeams = new ArrayList<>();
//        listTeams.add(team);
//        match.setTeams(listTeams);
//
//        List<Team> listTeamsOfPlayer = new ArrayList<>();
//        listTeams.add(team);
//        player.setTeams(listTeamsOfPlayer);

//        teamRepository.save(team);
        match.setGround(ground);
        matchRepository.save(match);
        playerRepository.save(player);
        reservationRepository.save(reservation);

        /**
         this is for notifying the owner that a match has been created !
         */
        NotificationOwner notificationOwner = new NotificationOwner();
        notificationOwner.setCreatedAt(LocalDateTime.now());
        notificationOwner.setReservation(reservation);
        notificationOwnerRepository.save(notificationOwner);

    }

    @Override
    public void joinMatchAsPlayer(TeamDto teamDto) {
        Optional<Team> teamOptional = teamRepository.findById(teamDto.getId());
        Optional<Player> playerOptional = playerRepository.findById(teamDto.getPlayer_id());
        if (teamOptional.isEmpty()) {
            throw new IllegalArgumentException("team not found");
        }
        if (playerOptional.isEmpty()) {
            throw new IllegalArgumentException("player not found ");
        }
        Team team = teamOptional.get();
        Player player = playerOptional.get();
        List<Player> listPlayers = team.getPlayers();
        listPlayers.add(player);
        team.setPlayers(listPlayers);
        teamRepository.save(team);

    }

    @Override
    public void joinMatchAsTeam(PlayerDto playerDto) {

    }

    @Override
    public List<MatchDto> getMatches() {
        return matchRepository.findAll().stream().map(matchMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void evaluateMatch(MatchDto matchDto) {

    }

}
