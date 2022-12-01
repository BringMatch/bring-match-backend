package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.entities.NotificationAdmin;
import com.example.testpfsentities.entities.Owner;
import com.example.testpfsentities.mapper.OwnerMapper;
import com.example.testpfsentities.repository.AdminRepository;
import com.example.testpfsentities.repository.NotificationAdminRepository;
import com.example.testpfsentities.repository.OwnerRepository;
import com.example.testpfsentities.service.OwnerService;
import com.example.testpfsentities.validations.OwnerValidator;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final AdminRepository adminRepository;
    private final NotificationAdminRepository notificationAdminRepository;
    private final OwnerMapper ownerMapper;
    private final OwnerValidator ownerValidator;

    @Override
    public void save(OwnerDto ownerDto) {
        ownerValidator.validateCreation(ownerDto);
        Owner owner=ownerMapper.toBo(ownerDto);
        ownerRepository.save(owner);
        List<Admin> adminList = adminRepository.findFirstAdmin();
        Admin admin=adminList.get(0);
        admin.getOwners().add(owner);
        adminRepository.save(admin);
        // call admin
       // adminRepository.saveOwner(admin.get(0).getId(), ownerMapper.toBo(ownerDto).getId());
        // comes the notification
        // we should create the notification
        NotificationAdmin notificationAdmin = new NotificationAdmin();
        notificationAdmin.setDelivered(true);
        notificationAdmin.setUserFrom(admin);
        notificationAdmin.setRead(false);
        notificationAdminRepository.save(notificationAdmin);
    }

    @Override
    public List<OwnerDto> getOwners() {
        var list = ownerRepository.findAll();
        List<OwnerDto> liste = new ArrayList<>();
        for (Owner owner : list) {
            liste.add(ownerMapper.toDto(owner));
        }
        return liste;
    }

}
