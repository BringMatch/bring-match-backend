package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.mapper.OwnerMapper;
import com.example.testpfsentities.repository.*;
import com.example.testpfsentities.scheduling.NewUserEvent;
import com.example.testpfsentities.service.AdminService;
import com.example.testpfsentities.service.NotificationAdminService;
import com.example.testpfsentities.service.OwnerService;
import com.example.testpfsentities.service.UserService;
import com.example.testpfsentities.validations.OwnerValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final NotificationAdminService notificationAdminService;
    private final OwnerMapper ownerMapper;
    private final OwnerValidator ownerValidator;
    private final AdminService adminService;
    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void save(OwnerDto ownerDto) {
        ownerValidator.validateCreation(ownerDto);
        Owner owner = ownerMapper.toBo(ownerDto);
        ownerRepository.save(owner);
        Admin admin = adminService.save(owner);

        // we will create him in keycloak !
        userService.create(ownerDto);

        notificationAdminService.save(admin,owner);

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
    public boolean checkOwnerExists(String owner_id) {
        Optional<Owner> ownerOptional = ownerRepository.findById(owner_id);
        return ownerOptional.isPresent();
    }

    @Override
    public void setActiveStatus(Owner owner, boolean b) {
        owner.setActive(b);
        ownerRepository.save(owner);
    }

    @Override
    public void delete(String owner_id) {
        ownerRepository.deleteById(owner_id);
    }


}
