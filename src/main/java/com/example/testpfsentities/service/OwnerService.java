package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.OwnerDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OwnerService {
    void save(OwnerDto ownerDto);
    List<OwnerDto> getOwners();


}
