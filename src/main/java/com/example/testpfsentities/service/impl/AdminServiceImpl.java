package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.entities.NotificationAdmin;
import com.example.testpfsentities.entities.Owner;
import com.example.testpfsentities.repository.AdminRepository;
import com.example.testpfsentities.repository.NotificationAdminRepository;
import com.example.testpfsentities.repository.OwnerRepository;
import com.example.testpfsentities.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final OwnerRepository ownerRepository;
    private final NotificationAdminRepository notificationAdminRepository;

    @Override
    public void initAdmin() {
        Admin admin = new Admin();
        admin.setEmail("yessinejawa@gmail.com");
        admin.setUpdatedAt(Date.from(Instant.now()));
        admin.setPassword("yessine");
        admin.setFirstName("ajaoua");
        admin.setLastName("ajaqsdfoua");
        admin.setPhoneNumber("45454");
        admin.setRoleName("admin");
        admin.setCreatedAt(Date.from(Instant.now()));
        adminRepository.save(admin);
    }

    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findFirstAdmin();
    }

    @Override
    public void updateStatusNotification(String notif_id) {
        Optional<NotificationAdmin> notificationAdmin = notificationAdminRepository.findById(notif_id);
        if (notificationAdmin.isEmpty()) {
            throw new IllegalArgumentException("Notification not existing !");
        }
        notificationAdmin.get().setRead(true);
        notificationAdminRepository.save(notificationAdmin.get());
    }


    @Override
    public void updateStatusOwnerWithTrue(String owner_id) {
        Optional<Owner> owner = ownerRepository.findById(owner_id);
        if (owner.isEmpty()) {
            throw new IllegalArgumentException("Owner not existing !");
        }
        owner.get().setActive(true);
        ownerRepository.save(owner.get());
    }

    @Override
    public void updateStatusOwnerWithFalse(String owner_id) {
        Optional<Owner> owner = ownerRepository.findById(owner_id);
        if (owner.isEmpty()) {
            throw new IllegalArgumentException("Owner not existing !");
        }
        owner.get().setActive(false);
        ownerRepository.save(owner.get());
    }

}
