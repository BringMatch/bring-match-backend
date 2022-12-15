package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.entities.NotificationAdmin;
import com.example.testpfsentities.entities.Owner;
import com.example.testpfsentities.repository.NotificationAdminRepository;
import com.example.testpfsentities.service.NotificationAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationAdminServiceImpl implements NotificationAdminService {
    private final NotificationAdminRepository notificationAdminRepository;

    @Override
    public List<NotificationAdmin> findAll() {
        return notificationAdminRepository.findAll();
    }

    @Override
    public void save(Admin admin, Owner owner) {
        NotificationAdmin notificationAdmin = new NotificationAdmin();
        notificationAdmin.setDelivered(true);
        notificationAdmin.setUserTo(admin);
        notificationAdmin.setUserFrom(owner);
        notificationAdmin.setRead(false);
        notificationAdmin.setMessage(setNotificationAdminMessage(owner));
        notificationAdminRepository.save(notificationAdmin);
    }

    private String setNotificationAdminMessage(Owner owner) {
        return owner.getLastName() + " " + owner.getFirstName() + " is joining our application !";
    }

    @Override
    public void acceptOwnerRequest(String notif_id) {
        var notificationAdmin = findNotificationById(notif_id);
        Owner owner = notificationAdmin.getUserFrom();
        notificationAdmin.setRead(true);
        owner.setActive(true);
//        ownerService.setActiveStatus(owner , true);
        notificationAdminRepository.save(notificationAdmin);
    }

    @Override
    public void refuseOwnerRequest(String notif_id) {
        var notificationAdmin = findNotificationById(notif_id);
        Owner owner = notificationAdmin.getUserFrom();
        notificationAdmin.setRead(true);
        owner.setActive(false);
//        ownerService.setActiveStatus(owner , false);
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
