package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.NotificationPlayerDto;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.NotificationPlayer;

import com.example.testpfsentities.entities.Player;
import com.example.testpfsentities.mapper.NotificationPlayerMapper;
import com.example.testpfsentities.repository.NotificationPlayerRepository;
import com.example.testpfsentities.service.NotificationPlayerService;
import com.example.testpfsentities.service.PlayerService;
import com.example.testpfsentities.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationPlayerServiceImpl implements NotificationPlayerService {
    private final NotificationPlayerRepository notificationPlayerRepository;
    private final NotificationPlayerMapper notificationPlayerMapper;
    private final PlayerService playerService;
    private final UserService userService;

    @Override
    public void save(NotificationPlayer notificationPlayer) {
        notificationPlayerRepository.save(notificationPlayer);
    }

    @Override
    public NotificationPlayer create(Match match, Player player) {
        var owner_match_player = playerService.returnOwnerMatchPlayer(match);
        NotificationPlayer notificationPlayer = new NotificationPlayer();
        notificationPlayer.setDelivered(true);
        notificationPlayer.setCreatedAt(Date.from(Instant.now()));
        notificationPlayer.setRead(false);
        notificationPlayer.setMessage(player.getUsername() + " has join the match which held at " + match.getDate());
        notificationPlayer.setPlayer(owner_match_player);
        notificationPlayer.setMatch(match);
        return notificationPlayerRepository.save(notificationPlayer);
    }

    @Override
    public List<NotificationPlayerDto> getNotifications() {
        var player = userService.getPlayerConnected();
        List<NotificationPlayer> listNotificationPlayer = new ArrayList<>();
        for (NotificationPlayer notificationPlayer : notificationPlayerRepository.findAll()) {
            if (notificationPlayer.getPlayer().getId().equals(player.getId())) {
                listNotificationPlayer.add(notificationPlayer);
            }
        }
        return notificationPlayerMapper.toDto(listNotificationPlayer);
    }

    @Override
    public List<NotificationPlayerDto> getNotifications(int max) {
        var player = userService.getPlayerConnected();
        var list = notificationPlayerRepository.findNotificationPlayerByOwner_matchAnd(player);
        return notificationPlayerMapper.toDto(list.subList(Math.max(list.size() - max, 0), list.size()));
    }

    @Override
    public void updateNotificationState(String notification_player_id) {
        var notificationPlayer = findById(notification_player_id);
        notificationPlayer.setRead(true);
        notificationPlayerRepository.save(notificationPlayer);
    }

    private NotificationPlayer findById(String notification_player_id) {
        Optional<NotificationPlayer> optionalNotificationPlayer = notificationPlayerRepository.findById(notification_player_id);
        if (optionalNotificationPlayer.isEmpty()) {
            throw new IllegalArgumentException("notification player not found !");
        }
        return optionalNotificationPlayer.get();
    }
}
