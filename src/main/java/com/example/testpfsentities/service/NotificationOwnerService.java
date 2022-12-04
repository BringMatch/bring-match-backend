package com.example.testpfsentities.service;

import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.entities.NotificationOwner;
import com.example.testpfsentities.entities.Reservation;
import org.springframework.stereotype.Service;

@Service
public interface NotificationOwnerService {
    NotificationOwner create(Reservation reservation , Ground ground);
    void save(NotificationOwner notificationOwner);
}
