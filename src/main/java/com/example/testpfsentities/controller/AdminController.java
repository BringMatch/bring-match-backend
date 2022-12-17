package com.example.testpfsentities.controller;

import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.service.AdminService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.ADMINS)
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(ApiPaths.GET_ADMINS)
    public ResponseEntity<List<Admin>> getAdmins() {
        return ResponseEntity.ok().body(adminService.getAdmins());
    }

    @PostConstruct
    public void initAdmin() {
        adminService.initAdmin();
    }

    @PutMapping(ApiPaths.UPDATE_NOTIF_ADMIN_READ_STATUS + "/{id}")
    public void updateReadNotification(@PathVariable(name = "id") String notif_id) {
        adminService.updateStatusNotification(notif_id);
    }

    @PutMapping(ApiPaths.UPDATE_STATUS_OWNER_TRUE + "/{owner_id}")
    public void updateActiveStatusOwnerWithTrue(@PathVariable(name = "owner_id") String owner_id) {
        adminService.updateStatusOwnerWithTrue(owner_id);
    }

    @PutMapping(ApiPaths.UPDATE_STATUS_OWNER_FALSE + "/{owner_id}")
    public void updateActiveStatusOwnerWithFalse(@PathVariable(name = "owner_id") String owner_id) {
        adminService.updateStatusOwnerWithFalse(owner_id);
    }



}
