package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.NotificationPlayer;

import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.repository.NotificationPlayerRepository;
import com.example.testpfsentities.service.NotificationPlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class NotificationPlayerServiceImpl implements NotificationPlayerService {
    private final NotificationPlayerRepository notificationPlayerRepository;

    @Override
    public void save(NotificationPlayer notificationPlayer) {
        notificationPlayerRepository.save(notificationPlayer);
    }

    @Override
    public NotificationPlayer create(Match match, Player player) {
        NotificationPlayer notificationPlayer = new NotificationPlayer();
        notificationPlayer.setDelivered(true);
        notificationPlayer.setCreatedAt(Date.from(Instant.now()));
        notificationPlayer.setRead(false);
        notificationPlayer.setPlayerJoined(player);
        notificationPlayer.setCurrentMatchId(match.getId());
        return notificationPlayerRepository.save(notificationPlayer);
    }
}
