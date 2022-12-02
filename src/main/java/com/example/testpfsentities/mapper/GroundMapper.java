package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.entities.Owner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GroundMapper {
    private final ModelMapper modelMapper;

    public Ground toBo(GroundDto groundDto) {
        return modelMapper.map(groundDto, Ground.class);
    }

    public GroundDto toDto(Ground ground) {
        return modelMapper.map(ground, GroundDto.class);
    }
}

