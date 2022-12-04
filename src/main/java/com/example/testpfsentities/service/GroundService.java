package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.entities.Ground;

import java.util.List;

public interface GroundService {
    List<GroundDto> getGrounds();

    Ground findByName(String ground_name);

    void updateGround(GroundDto groundDto);

    void saveGround(GroundDto groundDto);

    List<GroundDto> getOwnerGrounds(String owner_id);
}
