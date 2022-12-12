package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.entities.Ground;

import java.util.List;

public interface GroundService {
    List<GroundDto> getGrounds();

    Ground findByName(String ground_name);

    void updateGround(GroundDto groundDto);

    void saveGround(GroundDto groundDto);
    List<GroundDto>  getAllGroundsByTownAndRegion(String town,String region);

    List<GroundDto> getOwnerGrounds(String owner_id);

    List<Ground> getOwnerGroundsBo(String owner_id);

    void updateStatusGround(GroundDto groundDto);

    void deleteGround(String ground_id);

    Integer getNumberOwnerGrounds(String owner_id);

    Integer getNumberGroundsOpen(String owner_id);

    Integer getNumberGroundsClosed(String owner_id);
}
