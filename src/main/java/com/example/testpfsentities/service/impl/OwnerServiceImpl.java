package com.example.testpfsentities.service.impl;

import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.mapper.OwnerMapper;
import com.example.testpfsentities.repository.OwnerRepository;
import com.example.testpfsentities.service.OwnerService;
import com.example.testpfsentities.validations.OwnerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;
    private final OwnerValidator ownerValidator;
    @Override
    public void save(OwnerDto ownerDto) {
        ownerValidator.validateCreation(ownerDto);
        ownerRepository.save(ownerMapper.toBo(ownerDto));
    }
}
