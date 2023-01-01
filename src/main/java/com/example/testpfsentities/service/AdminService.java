package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.entities.Owner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdminService {
    void initAdmin();

    List<Admin> getAdmins();

    void updateStatusNotification(String notif_id);

    void updateStatusOwnerWithTrue(String owner_id);

    void updateStatusOwnerWithFalse(String owner_id);

    Admin save(Owner owner);

    List<OwnerDto> getAcceptedOwners();

    List<OwnerDto> getRefusedOwners();
    List<OwnerDto> getAllOwners();

    Integer getAllPlayers();
}
