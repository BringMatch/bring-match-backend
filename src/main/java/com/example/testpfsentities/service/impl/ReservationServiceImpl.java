package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.entities.Reservation;
import com.example.testpfsentities.service.ReservationRepository;
import com.example.testpfsentities.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    @Override
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public Reservation create(MatchDto matchDto, Ground ground) {
        Reservation reservation = new Reservation();
        reservation.setTeam_one_creator_id(matchDto.getTeams().get(0).getPlayersTeams().get(0).getPlayer().getId());
        reservation.setTeam_two_creator_id(null);
        reservation.setStatus(false);
        reservation.setGround(ground);
        return reservationRepository.save(reservation);
    }
}
