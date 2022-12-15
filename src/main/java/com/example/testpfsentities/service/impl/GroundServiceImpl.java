package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.GroundSearchDto;
import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.mapper.GroundMapper;
import com.example.testpfsentities.repository.GroundRepository;
import com.example.testpfsentities.service.GroundService;
import com.example.testpfsentities.service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroundServiceImpl implements GroundService {
    private final GroundRepository groundRepository;
    private final GroundMapper groundMapper;
    private final OwnerService ownerService;

    @Override
    public List<GroundDto> getGrounds() {
        return groundRepository.findAll().stream().map(groundMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Ground findByName(String ground_name) {
        Optional<Ground> groundOptional = groundRepository.findByName(ground_name);
        if (groundOptional.isEmpty()) {
            throw new IllegalArgumentException("ground not existing !");
        }

        return groundOptional.get();
    }

    public Ground findById(String ground_id) {
        Optional<Ground> groundOptional = groundRepository.findById(ground_id);
        if (groundOptional.isEmpty()) {
            throw new IllegalArgumentException("ground not found !");
        }
        return groundOptional.get();
    }

    @Override
    public void updateGround(GroundDto groundDto) {

    }

    @Override
    public void saveGround(GroundDto groundDto) {
        String owner_id = groundDto.getOwner().getId();
        if (ownerService.checkOwnerExists(owner_id)) {
            groundRepository.save(groundMapper.toBo(groundDto));
        } else {
            throw new IllegalArgumentException("owner not existing !");
        }
    }

    @Override
    public List<GroundDto> getAllGroundsByTownAndRegion(GroundSearchDto groundSearchDto) {
        return groundMapper.toDto(groundRepository.findAllGroundByTownAndRegion(
                groundSearchDto.getTown(),
                groundSearchDto.getRegion()
        ));
    }

    @Override
    public List<GroundDto> getOwnerGrounds(String owner_id) {
        return groundRepository.findByOwner_Id(owner_id).stream().map((groundMapper::toDto))
                .collect(Collectors.toList());
    }

    public List<Ground> getOwnerGroundsBo(String owner_id) {
        return groundRepository.findByOwner_Id(owner_id);
    }

    @Override
    public void updateStatusGround(GroundDto groundDto) {
        Ground ground = findById(groundDto.getId());
        ground.setStatus(groundDto.isStatus());
        groundRepository.save(ground);
    }

    @Override
    public void deleteGround(String ground_id) {
        Ground ground = findById(ground_id);
        groundRepository.delete(ground);
    }

    @Override
    public Integer getNumberOwnerGrounds(String owner_id) {
        log.info(String.valueOf(groundRepository.findByOwner_Id(owner_id).size()));
        boolean ownerExists = ownerService.checkOwnerExists(owner_id);
        if (!ownerExists) {
            throw new IllegalArgumentException("owner not existing ");
        }
        return groundRepository.findByOwner_Id(owner_id).size();
    }

    @Override
    public Integer getNumberGroundsOpen(String owner_id) {
        boolean ownerExists = ownerService.checkOwnerExists(owner_id);
        if (!ownerExists) {
            throw new IllegalArgumentException("owner not existing ");
        }
        return (int) groundRepository.findByOwner_Id(owner_id).stream().filter(Ground::isStatus).count();
    }

    @Override
    public Integer getNumberGroundsClosed(String owner_id) {
        boolean ownerExists = ownerService.checkOwnerExists(owner_id);
        if (!ownerExists) {
            throw new IllegalArgumentException("owner not existing ");
        }
        return (int) groundRepository.findByOwner_Id(owner_id).stream().filter(ground ->
                !ground.isStatus()).count();
    }

    @Override
    public boolean getGroundStatusById(Ground ground) {
        return ground.isStatus();
    }
}
