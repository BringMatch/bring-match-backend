package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.email.EmailSenderForOwner;
import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.entities.NotificationAdmin;
import com.example.testpfsentities.entities.Owner;
import com.example.testpfsentities.entities.enums.Role;
import com.example.testpfsentities.mapper.OwnerMapper;
import com.example.testpfsentities.repository.AdminRepository;
import com.example.testpfsentities.repository.NotificationAdminRepository;
import com.example.testpfsentities.repository.OwnerRepository;
import com.example.testpfsentities.repository.PlayerRepository;
import com.example.testpfsentities.service.AdminService;
import com.example.testpfsentities.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final OwnerRepository ownerRepository;
    private final NotificationAdminRepository notificationAdminRepository;
    private final EmailSenderForOwner emailSenderForOwner;
    private final OwnerMapper ownerMapper;
    private final UserService userService;
    private final PlayerRepository playerRepository;

    @Override
    public void initAdmin() {
//        Admin admin = new Admin();
//        admin.setEmail("bardichefatiha@gmail.com");
//        admin.setUpdatedAt(Date.from(Instant.now()));
//        admin.setPassword("admin");
//        admin.setFirstName("haha");
//        admin.setLastName("haha");
//        admin.setPhoneNumber("45454");
//        admin.setUsername("yessine");
//        admin.setRole(Role.ADMIN);
//        admin.setCreatedAt(Date.from(Instant.now()));
//        adminRepository.save(admin);
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
        Optional<Owner> ownerOptional = ownerRepository.findById(owner_id);
        if (ownerOptional.isEmpty()) {
            throw new IllegalArgumentException("Owner not existing !");
        }
        Owner owner = ownerOptional.get();
        owner.setActive(true);
        ownerRepository.save(owner);

        userService.enableUser(owner.getId());

        String subject = "compte créé avec succes";
        String Body = "Bienvenue chez Bring Match " + System.lineSeparator() +
                "Merci pour votre inscription à notre application " + System.lineSeparator() +
                "votre inscription est acceptée" + System.lineSeparator() +
                "Merci de ne pas répondre a cette email";

        emailSenderForOwner.sendEmail(owner.getEmail(), subject, Body);
    }

    private NotificationAdmin findNotificationAdminById(String notification_id) {

        Optional<NotificationAdmin> notificationAdminOptional = notificationAdminRepository.findById(notification_id);
        if (notificationAdminOptional.isEmpty()) {
            throw new IllegalArgumentException("Notif not existing !");
        }
        return notificationAdminOptional.get();
    }

    @Override
    public void updateStatusOwnerWithFalse(String owner_id) {
        Optional<Owner> ownerOptional = ownerRepository.findById(owner_id);
        if (ownerOptional.isEmpty()) {
            throw new IllegalArgumentException("Owner not existing !");
        }
        Owner owner = ownerOptional.get();
        owner.setActive(false);
        ownerRepository.save(owner);

        userService.disableUser(owner.getId());

        String subject = "compte non créé ";
        String Body = "Désolé" + System.lineSeparator() +
                "Merci pour votre inscription à notre application " + System.lineSeparator() +
                "votre inscription est refusée " + System.lineSeparator() +
                "Merci de ne pas répondre a cette email";

        emailSenderForOwner.sendEmail(owner.getEmail(), subject, Body);
    }

    @Override
    public Admin save(Owner owner) {
        List<Admin> adminList = adminRepository.findFirstAdmin();
        Admin admin = adminList.get(0);
        admin.getOwners().add(owner);
        return admin;
    }

    @Override
    public List<OwnerDto> getAcceptedOwners() {
        var admin = userService.getAdminConnected();
        List<Owner> listAcceptedOwners = new ArrayList<>();
        for (Owner owner : admin.getOwners()) {
            if (owner.isActive()) {
                listAcceptedOwners.add(owner);
            }
        }
        return ownerMapper.toDto(listAcceptedOwners);
    }

    @Override
    public List<OwnerDto> getRefusedOwners() {
        var admin = userService.getAdminConnected();
        List<Owner> listAcceptedOwners = new ArrayList<>();
        for (Owner owner : admin.getOwners()) {
            if (!owner.isActive()) {
                listAcceptedOwners.add(owner);
            }
        }
        return ownerMapper.toDto(listAcceptedOwners);
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        var admin = userService.getAdminConnected();
        List<Owner> listAcceptedOwners = new ArrayList<>(admin.getOwners());
        return ownerMapper.toDto(listAcceptedOwners);
    }

    @Override
    public Integer getAllPlayers() {
        return playerRepository.findAll().size();
    }

}
