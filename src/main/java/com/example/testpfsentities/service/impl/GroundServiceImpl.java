package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.mapper.GroundMapper;
import com.example.testpfsentities.repository.GroundRepository;
import com.example.testpfsentities.service.GroundService;
import com.example.testpfsentities.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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
    public List<GroundDto> getOwnerGrounds(String owner_id) {
        return groundRepository.findByOwner_Id(owner_id).stream().map((ground -> groundMapper.toDto(ground)))
                .collect(Collectors.toList());
    }
}
