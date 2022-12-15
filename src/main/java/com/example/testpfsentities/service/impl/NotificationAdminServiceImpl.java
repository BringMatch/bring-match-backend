package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.entities.NotificationAdmin;
import com.example.testpfsentities.entities.Owner;
import com.example.testpfsentities.repository.NotificationAdminRepository;
import com.example.testpfsentities.service.NotificationAdminService;
import com.example.testpfsentities.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationAdminServiceImpl implements NotificationAdminService {
    private final NotificationAdminRepository notificationAdminRepository;
    private final OwnerService ownerService;

    @Override
    public List<NotificationAdmin> findAll() {
        return notificationAdminRepository.findAll();
    }

    @Override
    public void save(Admin admin) {
        NotificationAdmin notificationAdmin = new NotificationAdmin();
        notificationAdmin.setDelivered(true);
        notificationAdmin.setUserTo(admin);
        notificationAdmin.setRead(false);
        notificationAdminRepository.save(notificationAdmin);
    }

    @Override
    public void acceptOwnerRequest(String notif_id) {
        var notificationAdmin = findNotificationById(notif_id);
        Owner owner = notificationAdmin.getUserFrom();
        notificationAdmin.setRead(true);
        ownerService.setActiveStatus(owner , true);
        notificationAdminRepository.save(notificationAdmin);
    }

    @Override
    public void refuseOwnerRequest(String notif_id) {
        var notificationAdmin = findNotificationById(notif_id);
        Owner owner = notificationAdmin.getUserFrom();
        notificationAdmin.setRead(true);
        ownerService.setActiveStatus(owner , false);
        notificationAdminRepository.save(notificationAdmin);
    }

    private NotificationAdmin findNotificationById(String notification_id) {
        Optional<NotificationAdmin> notificationAdminOptional = notificationAdminRepository.findById(notification_id);
        if (notificationAdminOptional.isEmpty()) {
            throw new IllegalArgumentException("notification not found !");
        }
        return notificationAdminOptional.get();
    }

}
