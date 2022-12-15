package com.example.testpfsentities.service;

import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.entities.NotificationAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationAdminService  {
    List<NotificationAdmin> findAll();

    void save(Admin admin);

    void acceptOwnerRequest(String notif_id);

    void refuseOwnerRequest(String notif_id);
}
