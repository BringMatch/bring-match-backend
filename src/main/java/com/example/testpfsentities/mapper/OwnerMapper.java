package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.entities.Owner;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerMapper {
    private final ModelMapper modelMapper;

    public Owner toBo(OwnerDto ownerDto) {
        return modelMapper.map(ownerDto, Owner.class);
    }

    public OwnerDto toDto(Owner owner) {
        return modelMapper.map(owner, OwnerDto.class);
    }
}
