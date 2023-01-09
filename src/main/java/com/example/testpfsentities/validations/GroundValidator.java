package com.example.testpfsentities.validations;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.exceptions.BusinessException;
import com.example.testpfsentities.exceptions.Message;
import com.example.testpfsentities.repository.GroundRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor()
@Slf4j
public class GroundValidator {
    private final GroundRepository groundRepository;

    public void validateCreation(GroundDto groundDto) {
        var list = groundRepository.findAll();
        for (Ground ground : list) {
            if (ground.getName().equals(groundDto.getName())) {
                throw new BusinessException(new Message("Ground not found!"));
            }
        }
    }

    public void validateUpdateGround(GroundDto groundDto) {

    }
}
