package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.entities.NotificationOwner;
import com.example.testpfsentities.entities.Reservation;
import com.example.testpfsentities.repository.NotificationOwnerRepository;
import com.example.testpfsentities.service.NotificationOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class NotificationOwnerServiceImpl implements NotificationOwnerService {
    private final NotificationOwnerRepository notificationOwnerRepository;
    @Override
    public NotificationOwner create(Reservation reservation , Ground ground) {
        NotificationOwner notificationOwner = new NotificationOwner();
        notificationOwner.setCreatedAt(Date.from(Instant.now()));
        notificationOwner.setReservation(reservation);
        notificationOwner.setOwner(ground.getOwner());
        return  notificationOwner;
    }

    @Override
    public void save(NotificationOwner notificationOwner) {
        notificationOwnerRepository.save(notificationOwner);
    }
}
