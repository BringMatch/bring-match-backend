package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.NotificationPlayerDto;
import com.example.testpfsentities.service.NotificationPlayerService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.NOTIFICATIONS)
@RequiredArgsConstructor
public class NotificationPlayerController {
    private final NotificationPlayerService notificationPlayerService;
    @GetMapping(ApiPaths.GET_NOTIFICATIONS_PLAYER)
    public ResponseEntity<List<NotificationPlayerDto>> getNotificationsPlayer(){
        return ResponseEntity.ok().body(notificationPlayerService.getNotifications());
    }

    @GetMapping(ApiPaths.GET_LAST_FIVE_NOTIFICATIONS_PLAYER)
    public ResponseEntity<List<NotificationPlayerDto>> getLastFiveNotificationPlayer(){
        return ResponseEntity.ok().body(notificationPlayerService.getNotifications(5));
    }
}
