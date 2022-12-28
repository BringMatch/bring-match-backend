package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.GroundSearchDto;
import com.example.testpfsentities.entities.Ground;

import java.util.List;

public interface GroundService {
    List<GroundDto> getGrounds();

    Ground findByName(String ground_name);

    void updateGround(GroundDto groundDto);

    void saveGround(GroundDto groundDto);
    List<GroundDto>  getAllGroundsByTownAndRegion(GroundSearchDto groundSearchDto);

    List<GroundDto> getOwnerGrounds();

    List<Ground> getOwnerGroundsBo(String owner_id);

    void updateStatusGround(GroundDto groundDto);

    void deleteGround(String ground_id);

    Integer getNumberOwnerGrounds();

    Integer getNumberGroundsOpen();

    Integer getNumberGroundsClosed();

    boolean getGroundOpenStatusById(Ground ground);

    List<GroundDto> getOpenAndFreeGrounds();
}
