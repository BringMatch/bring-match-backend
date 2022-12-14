package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.GroundSearchDto;
import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.exceptions.BusinessException;
import com.example.testpfsentities.exceptions.Message;
import com.example.testpfsentities.mapper.GroundMapper;
import com.example.testpfsentities.repository.GroundRepository;
import com.example.testpfsentities.service.GroundService;
import com.example.testpfsentities.service.OwnerService;
import com.example.testpfsentities.service.UserService;
import com.example.testpfsentities.validations.GroundValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroundServiceImpl implements GroundService {
    private final GroundRepository groundRepository;
    private final GroundValidator groundValidator;
    private final GroundMapper groundMapper;
    private final OwnerService ownerService;
    private final UserService userService;

    @Override
    public List<GroundDto> getGrounds() {
        return groundRepository.findAll().stream().map(groundMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Ground findByName(String ground_name) {
        Optional<Ground> groundOptional = groundRepository.findByName(ground_name);
        if (groundOptional.isEmpty()) {
            throw new BusinessException(new Message("ground not found !"));
        }

        return groundOptional.get();
    }

    public Ground findById(String ground_id) {
        Optional<Ground> groundOptional = groundRepository.findById(ground_id);
        if (groundOptional.isEmpty()) {
            throw new BusinessException(new Message("ground not found !"));
        }
        return groundOptional.get();
    }

    @Override
    public void updateGround(GroundDto groundDto) {
//        groundValidator.validateGround(groundDto);
        groundRepository.save(groundMapper.toBo(groundDto));
        log.info("ground updated !");
    }

    @Override
    public Ground saveGround(GroundDto groundDto) {
        groundValidator.validateCreation(groundDto);
        var owner = userService.getOwnerBoConnected();
        if (owner != null){
            var ground = groundRepository.save(groundMapper.toBo(groundDto));
            ground.setOpen(true);
            ground.setOwner(owner);
            return groundRepository.save(ground);
        } else{
            throw new BusinessException(new Message("ground not found !"));
        }
    }


    @Override
    public List<GroundDto> getAllGroundsByTownAndRegion(GroundSearchDto groundSearchDto) {
        if (groundSearchDto.getTown() == null && groundSearchDto.getName() == null) {
            log.info("here");
            return groundMapper.toDto(groundRepository.findAll());

        } else {
            log.info("heremee");
            return groundMapper.toDto(groundRepository.findAllGroundByTownAndRegion(
                    groundSearchDto.getTown(),
                    groundSearchDto.getName()
            ));
        }
    }

    @Override
    public List<GroundDto> getOwnerGrounds() {
        var owner = userService.getOwnerBoConnected();
        var grounds = groundRepository.findByOwner_Id(owner.getId());
        if (grounds.size() == 0) {
            throw new BusinessException(new Message("owner not found !"));
        }
        return grounds.stream().map((groundMapper::toDto))
                .collect(Collectors.toList());
    }

    public List<Ground> getOwnerGroundsBo(String owner_id) {
        return groundRepository.findByOwner_Id(owner_id);
    }

    @Override
    public void updateStatusGround(GroundDto groundDto) {
        Ground ground = findById(groundDto.getId());
        ground.setOpen(groundDto.isOpen());
        groundRepository.save(ground);
    }

    @Override
    public void deleteGround(String ground_id) {
        Ground ground = findById(ground_id);
        groundRepository.delete(ground);
    }

    @Override
    public Integer getNumberOwnerGrounds() {
        var owner = userService.getOwnerBoConnected();
        String owner_id = owner.getId();
        log.info(String.valueOf(groundRepository.findByOwner_Id(owner_id).size()));
        boolean ownerExists = ownerService.checkOwnerExists(owner_id);
        if (!ownerExists) {
            throw new BusinessException(new Message("owner not found !"));
        }
        return groundRepository.findByOwner_Id(owner_id).size();
    }

    @Override
    public Integer getNumberGroundsOpen() {
        var owner = userService.getOwnerBoConnected();
        String owner_id = owner.getId();
        boolean ownerExists = ownerService.checkOwnerExists(owner_id);
        if (!ownerExists) {
            throw new BusinessException(new Message("owner not found !"));
        }
        return (int) groundRepository.findByOwner_Id(owner_id).stream().filter(Ground::isOpen).count();
    }

    @Override
    public Integer getNumberGroundsClosed() {
        var owner = userService.getOwnerBoConnected();
        String owner_id = owner.getId();
        boolean ownerExists = ownerService.checkOwnerExists(owner_id);
        if (!ownerExists) {
            throw new BusinessException(new Message("owner not found !"));
        }

        return (int) groundRepository.findByOwner_Id(owner_id).stream().filter(ground ->
                !ground.isOpen()).count();
    }

    @Override
    public boolean getGroundOpenStatusById(Ground ground) {
        return ground.isOpen();
    }


    @Override
    public List<GroundDto> getOpenAndFreeGrounds() {
        var listGrounds = groundRepository.findAll();
        List<Ground> finalGroundList = new ArrayList<>();
        for (Ground ground : listGrounds) {
            if ( ground.isOpen()) {
                finalGroundList.add(ground);
            }
        }
        return groundMapper.toDto(finalGroundList);
    }
}
