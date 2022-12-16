package com.example.testpfsentities.controller;

import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.service.AdminService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.ADMINS)
@RequiredArgsConstructor
//@RolesAllowed("ADMIN")
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

    @PutMapping(ApiPaths.UPDATE_STATUS_OWNER_FALSE + "/{owner_id}")
    public void updateActiveStatusOwnerWithFalse(@PathVariable(name = "owner_id") String owner_id) {
        adminService.updateStatusOwnerWithFalse(owner_id);
    }

    @GetMapping(ApiPaths.GET_ACCEPTED_OWNERS + "/{admin_id}")
    public ResponseEntity<List<OwnerDto>> getAcceptedOwners(@PathVariable(name = "admin_id")String admin_id){
        return ResponseEntity.ok().body(adminService.getAcceptedOwners(admin_id));
    }

    @GetMapping(ApiPaths.GET_REFUSED_OWNERS + "/{admin_id}")
    public ResponseEntity<List<OwnerDto>> getRefusedOwners(@PathVariable(name = "admin_id")String admin_id){
        return ResponseEntity.ok().body(adminService.getRefusedOwners(admin_id));
    }

    @GetMapping(ApiPaths.GET_ALL_OWNERS + "/{admin_id}")
    public ResponseEntity<List<OwnerDto>> getAllOwners(@PathVariable(name = "admin_id")String admin_id){
        return ResponseEntity.ok().body(adminService.getAllOwners(admin_id));
    }

}
