package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.entities.Reservation;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService {
    void save(Reservation reservation);

    Reservation create(MatchDto matchDto, Ground ground);
}
