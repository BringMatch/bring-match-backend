package com.example.testpfsentities.controller;

import com.example.testpfsentities.entities.NotificationAdmin;
import com.example.testpfsentities.service.NotificationAdminService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(ApiPaths.ACCEPT_NOTIF_ADMIN+"/{notif_id}")
    public void acceptOwnerRequest(@PathVariable(name = "notif_id") String notif_id){
        notificationAdminService.acceptOwnerRequest(notif_id);
    }

    @PutMapping(ApiPaths.REFUSE_NOTIF_ADMIN+"/{notif_id}")
    public void refuseOwnerRequest(@PathVariable(name = "notif_id") String notif_id){
        notificationAdminService.refuseOwnerRequest(notif_id);
    }
}
