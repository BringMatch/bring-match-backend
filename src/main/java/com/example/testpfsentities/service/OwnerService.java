package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.entities.Owner;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OwnerService {
    void save(OwnerDto ownerDto);
    List<OwnerDto> getOwners();

    boolean checkOwnerExists(String owner_id);

    void setActiveStatus(Owner owner, boolean b);
}
