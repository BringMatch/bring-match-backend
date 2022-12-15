package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.NotificationPlayerDto;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.NotificationPlayer;

import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.mapper.NotificationPlayerMapper;
import com.example.testpfsentities.repository.NotificationPlayerRepository;
import com.example.testpfsentities.service.NotificationPlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationPlayerServiceImpl implements NotificationPlayerService {
    private final NotificationPlayerRepository notificationPlayerRepository;
    private final NotificationPlayerMapper notificationPlayerMapper;

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
        notificationPlayer.setOwner_match(player);
        notificationPlayer.setCurrentMatchId(match.getId());
        return notificationPlayerRepository.save(notificationPlayer);
    }

    @Override
    public List<NotificationPlayerDto> getNotifications(String player_id) {
        return notificationPlayerMapper.toDto(notificationPlayerRepository.findAll());
    }
}
