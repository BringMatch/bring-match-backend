package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.entities.Ground;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Ground> toBo(List<GroundDto> groundDto) {
        return groundDto
                .stream()
                .map(element -> modelMapper.map(element, Ground.class))
                .collect(Collectors.toList());
    }

    public List<GroundDto> toDto(List<Ground> ground) {
        return ground
                .stream()
                .map(element -> modelMapper.map(element, GroundDto.class))
                .collect(Collectors.toList());
    }
}

