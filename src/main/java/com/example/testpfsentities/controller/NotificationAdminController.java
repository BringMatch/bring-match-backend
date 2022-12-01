package com.example.testpfsentities.controller;

import com.example.testpfsentities.entities.NotificationAdmin;
import com.example.testpfsentities.service.NotificationAdminService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPaths.NOTIF_ADMINS)
@RequiredArgsConstructor
public class NotificationAdminController {
    private final NotificationAdminService notificationAdminService;

    @GetMapping(ApiPaths.GET_NOTIF_ADMINS)
    public ResponseEntity<List<NotificationAdmin>> getListNotification() {
        return ResponseEntity.ok().body(notificationAdminService.findAll());
    }
}
