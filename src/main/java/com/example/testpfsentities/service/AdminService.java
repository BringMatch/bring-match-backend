package com.example.testpfsentities.service;

import com.example.testpfsentities.entities.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    void initAdmin();
    List<Admin> getAdmins();

    void updateStatusNotification(String notif_id);

    void updateStatusOwnerWithTrue(String owner_id);
    void updateStatusOwnerWithFalse(String owner_id);
}
