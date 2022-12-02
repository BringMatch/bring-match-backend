package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.entities.Owner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OwnerMapper {
    private final ModelMapper modelMapper;

    public Owner toBo(OwnerDto ownerDto) {
        return modelMapper.map(ownerDto, Owner.class);
    }

    public OwnerDto toDto(Owner owner) {
        return modelMapper.map(owner, OwnerDto.class);
    }
}
