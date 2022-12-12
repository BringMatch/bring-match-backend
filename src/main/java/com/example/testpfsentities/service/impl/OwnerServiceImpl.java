package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.mapper.GroundMapper;
import com.example.testpfsentities.mapper.OwnerMapper;
import com.example.testpfsentities.repository.*;
import com.example.testpfsentities.scheduling.NewUserEvent;
import com.example.testpfsentities.service.OwnerService;
import com.example.testpfsentities.validations.OwnerValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final AdminRepository adminRepository;
    private final NotificationAdminRepository notificationAdminRepository;
    private final OwnerMapper ownerMapper;
    private final GroundMapper groundMapper;
    private final OwnerValidator ownerValidator;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void save(OwnerDto ownerDto) {
        ownerValidator.validateCreation(ownerDto);
        Owner owner = ownerMapper.toBo(ownerDto);
        ownerRepository.save(owner);
        List<Admin> adminList = adminRepository.findFirstAdmin();
        Admin admin = adminList.get(0);
        admin.getOwners().add(owner);
        adminRepository.save(admin);
        NotificationAdmin notificationAdmin = new NotificationAdmin();
        notificationAdmin.setDelivered(true);
        notificationAdmin.setUserFrom(admin);
        notificationAdmin.setRead(false);
        notificationAdminRepository.save(notificationAdmin);
        applicationEventPublisher.publishEvent(new NewUserEvent(this, owner));
    }

    @Override
    public List<OwnerDto> getOwners() {
        var listBoOwners = ownerRepository.findAll();
        List<OwnerDto> listDtoOwners = new ArrayList<>();
        for (Owner owner : listBoOwners) {
            listDtoOwners.add(ownerMapper.toDto(owner));
        }
        return listDtoOwners;
    }

    @Override
    public List<GroundDto> getGrounds(String owner_id) {
        Optional<Owner> ownerOptional = ownerRepository.findById(owner_id);
        if (ownerOptional.isEmpty()) {
            throw new IllegalArgumentException("owner not existing !");
        }
        Owner owner = ownerOptional.get();
        return owner.getGrounds().stream().map(groundMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public boolean checkOwnerExists(String owner_id) {
        Optional<Owner> ownerOptional = ownerRepository.findById(owner_id);
        return ownerOptional.isPresent();
    }

}
