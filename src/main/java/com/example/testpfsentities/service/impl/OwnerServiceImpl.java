package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.mapper.GroundMapper;
import com.example.testpfsentities.mapper.OwnerMapper;
import com.example.testpfsentities.repository.*;
import com.example.testpfsentities.scheduling.NewUserEvent;
import com.example.testpfsentities.service.*;
import com.example.testpfsentities.validations.OwnerValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerServiceImpl implements OwnerService {
    private final GroundRepository groundRepository;
    private final OwnerRepository ownerRepository;
    private final NotificationAdminService notificationAdminService;
    private final OwnerMapper ownerMapper;
    private final OwnerValidator ownerValidator;
    private final AdminService adminService;
    private final UserService userService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UploadImageService uploadImageService;
    private final GroundMapper groundMapper;

    @Override
    public void save(OwnerDto ownerDto) throws IOException {
        ownerValidator.validateCreation(ownerDto);
        Owner owner = ownerMapper.toBo(ownerDto);

//        String url_image = uploadImageService.getUrlImage(owner.getGrounds().get(0).get);
        String url_image = "kjh";
        Ground ground = groundMapper.toBo(ownerDto.getGrounds().get(0));
        ground.setImage(url_image);
        groundRepository.save(ground);
        ownerRepository.save(owner);
        owner.setGrounds(List.of(ground));
        Admin admin = adminService.save(owner);
        // we will create him in keycloak !
        userService.create(ownerDto);
        notificationAdminService.save(admin, owner);
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
