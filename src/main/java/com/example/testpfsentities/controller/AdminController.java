package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.service.AdminService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.ADMINS)
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8081")
@Slf4j
public class AdminController {
    private final AdminService adminService;

    @GetMapping(ApiPaths.GET_ADMINS)
    public ResponseEntity<List<Admin>> getAdmins(
            @RequestHeader String Authorization
    ) {
        log.info(Authorization);
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

    @PutMapping(ApiPaths.UPDATE_STATUS_OWNER_FALSE + "/{notification_id}")
    public void updateActiveStatusOwnerWithFalse(@PathVariable(name = "notification_id") String notification_id) {
        adminService.updateStatusOwnerWithFalse(notification_id);
    }


    @GetMapping(ApiPaths.GET_NUMBER_ALL_OWNERS)
    public ResponseEntity<Integer> getAcceptedOwners() {
        return ResponseEntity.ok().body(adminService.getAllOwners().size());
    }

    @GetMapping(ApiPaths.GET_NUMBER_REFUSED_OWNERS)
    public ResponseEntity<Integer> getRefusedOwners() {
        return ResponseEntity.ok().body(adminService.getRefusedOwners().size());
    }

    @GetMapping(ApiPaths.GET_NUMBER_ACCEPTED_OWNERS)
    public ResponseEntity<Integer> getNumberOwners() {
        return ResponseEntity.ok().body(adminService.getAcceptedOwners().size());
    }

    @GetMapping(ApiPaths.GET_NUMBER_PLAYERS_SAVED)
    public ResponseEntity<Integer> getAllPlayers() {
        return ResponseEntity.ok().body(adminService.getAllPlayers());
    }

    @GetMapping(ApiPaths.OWNERS)
    public ResponseEntity<List<OwnerDto>> getAdminOwners() {
        return ResponseEntity.ok().body(adminService.getAllOwners());
    }

}
