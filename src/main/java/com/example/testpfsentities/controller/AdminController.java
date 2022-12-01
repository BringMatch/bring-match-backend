package com.example.testpfsentities.controller;

import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.service.AdminService;
import com.example.testpfsentities.utils.consts.ApiPaths;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.ADMINS)
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(ApiPaths.GET_ADMINS)
    public ResponseEntity<List<Admin>> getAdmins() {
        return ResponseEntity.ok().body(adminService.getAdmins());
    }

    @PostConstruct
    public void initAdmin() {
        adminService.initAdmin();
    }

    @PutMapping(ApiPaths.UPDATE_NOTIF_ADMIN_READ_STATUS + "/{id}")
    public void updateRead(@PathVariable(name = "id") String notif_id) {
        adminService.updateStatusNotification(notif_id);
    }
}
