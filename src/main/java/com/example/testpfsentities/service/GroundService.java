package com.example.testpfsentities.service;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.entities.Ground;

import java.util.List;

public interface GroundService {
    List<GroundDto> getGrounds();
}
