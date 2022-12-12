package com.example.testpfsentities.service;

import com.example.testpfsentities.entities.NotificationPlayer;
import com.example.testpfsentities.entities.Player;
import org.springframework.stereotype.Service;

@Service
public interface NotificationPlayerService {
    void save(NotificationPlayer notificationPlayer);
    NotificationPlayer create(String match_id, Player player);
}
