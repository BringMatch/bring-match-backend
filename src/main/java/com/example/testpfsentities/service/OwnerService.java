package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.OwnerDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OwnerService {
    void save(OwnerDto ownerDto);
    List<OwnerDto> getOwners();

    List<GroundDto> getGrounds(String owner_id);

    boolean checkOwnerExists(String owner_id);

}
