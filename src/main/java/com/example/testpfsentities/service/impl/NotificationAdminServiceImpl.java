package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.entities.NotificationAdmin;
import com.example.testpfsentities.repository.NotificationAdminRepository;
import com.example.testpfsentities.service.NotificationAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationAdminServiceImpl implements NotificationAdminService {
    private final NotificationAdminRepository notificationAdminRepository;

    @Override
    public List<NotificationAdmin> findAll() {
        return notificationAdminRepository.findAll();
    }
}
