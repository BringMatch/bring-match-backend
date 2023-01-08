package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.NotificationPlayerDto;
import com.example.testpfsentities.entities.Match;
import com.example.testpfsentities.entities.NotificationPlayer;
import com.example.testpfsentities.entities.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationPlayerService {
    void save(NotificationPlayer notificationPlayer);
    NotificationPlayer create(Match match , Player player );

    List<NotificationPlayer> getNotifications();
    List<NotificationPlayerDto> getNotifications(int max);

    void updateNotificationState(String notification_player_id);

}
