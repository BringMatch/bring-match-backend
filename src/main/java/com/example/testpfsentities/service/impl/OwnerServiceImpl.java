package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.entities.*;
import com.example.testpfsentities.mapper.GroundMapper;
import com.example.testpfsentities.mapper.MatchMapper;
import com.example.testpfsentities.mapper.OwnerMapper;
import com.example.testpfsentities.repository.*;
import com.example.testpfsentities.scheduling.NewUserEvent;
import com.example.testpfsentities.service.OwnerService;
import com.example.testpfsentities.validations.GroundValidator;
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
    private final MatchMapper matchMapper;
    private final OwnerValidator ownerValidator;
    private final GroundValidator groundValidator;
    private final GroundRepository groundRepository;
    private final MatchRepository matchRepository;
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
    public void saveGround(GroundDto groundDto) {
        Optional<Ground> optionalGround = groundRepository.findByName(groundDto.getName());
        if (optionalGround.isPresent()) {
            throw new IllegalArgumentException("Name Ground already existing !");
        }
        Ground ground = groundMapper.toBo(groundDto);
        Optional<Owner> ownerOptional = ownerRepository.findById(groundDto.getOwner().getId());
        if (ownerOptional.isEmpty()) {
            throw new IllegalArgumentException("owner not existing !");
        }
        ground.setOwner(ownerOptional.get());
        groundRepository.save(ground);
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
    public List<MatchDto> getMatchesGround(String ground_id) {
        Optional<Ground> groundOptional = groundRepository.findById(ground_id);
        if (groundOptional.isEmpty()) {
            throw new IllegalArgumentException("owner not existing !");
        }
        Ground ground = groundOptional.get();
        return ground.getMatches().stream().map(matchMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void updateGround(GroundDto groundDto) {
        groundValidator.validateUpdateGround(groundDto);
        groundRepository.save(groundMapper.toBo(groundDto));
        log.info("ground updated !");
    }

    @Override
    public void deleteMatch(MatchDto matchDto) {
        Optional<Match> matchOptional = matchRepository.findById(matchDto.getId());
        if (matchOptional.isEmpty()) {
            throw new IllegalArgumentException("match not existing !");
        }
        Match match = matchOptional.get();

        Optional<Ground> groundOptional = groundRepository.findByName(matchDto.getGroundName());
        if (groundOptional.isEmpty()) {
            throw new IllegalArgumentException("owner not existing !");
        }
        Ground ground = groundOptional.get();
        ground.setMatches(ground.getMatches().stream().filter(match1
                        -> match1.getId().equals(matchDto.getId()))
                .collect(Collectors.toList()));

        matchRepository.delete(match);
        log.info("match deleted !");
    }

    @Override
    public boolean checkOwnerExists(String owner_id) {
        Optional<Owner> ownerOptional = ownerRepository.findById(owner_id);
        return ownerOptional.isPresent();
    }

}
