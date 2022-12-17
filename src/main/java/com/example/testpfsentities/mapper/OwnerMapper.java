package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.entities.Owner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Owner> toBo(List<OwnerDto> ownerDtoList) {
        return ownerDtoList
                .stream()
                .map(element -> modelMapper.map(element, Owner.class))
                .collect(Collectors.toList());
    }

    public List<OwnerDto> toDto(List<Owner> owners) {
        return owners
                .stream()
                .map(element -> modelMapper.map(element, OwnerDto.class))
                .collect(Collectors.toList());
    }
}
