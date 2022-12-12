package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.entities.NotificationPlayer;

import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.repository.NotificationPlayerRepository;
import com.example.testpfsentities.service.NotificationPlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationPlayerServiceImpl implements NotificationPlayerService {
    private final NotificationPlayerRepository notificationPlayerRepository;

    @Override
    public void save(NotificationPlayer notificationPlayer) {
        notificationPlayerRepository.save(notificationPlayer);
    }

    @Override
    public NotificationPlayer create(String match_id, Player player) {
        NotificationPlayer notificationPlayer=new NotificationPlayer();
        notificationPlayer.setOwner_match(player);
       // notificationPlayer.setCreatedAt(LocalDateTime.now());
        notificationPlayer.setCurrentMatchId(match_id);
        return notificationPlayer;
    }
}
