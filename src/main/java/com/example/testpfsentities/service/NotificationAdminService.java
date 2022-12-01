package com.example.testpfsentities.service;

import com.example.testpfsentities.entities.NotificationAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationAdminService  {
    List<NotificationAdmin> findAll();
}
