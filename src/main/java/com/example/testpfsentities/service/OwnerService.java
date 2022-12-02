package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.dto.OwnerDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OwnerService {
    void save(OwnerDto ownerDto);
    List<OwnerDto> getOwners();


    void saveGround(GroundDto groundDto);

    List<GroundDto> getGrounds(String owner_id);

    List<MatchDto> getMatchesGround(String ground_id);

    void updateGround(GroundDto groundDto);

    void deleteMatch(MatchDto matchDto);
}
