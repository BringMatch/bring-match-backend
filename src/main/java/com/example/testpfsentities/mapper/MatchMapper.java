package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.entities.Match;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MatchMapper {
    private final ModelMapper modelMapper;

    public Match toBo(MatchDto matchDto) {
        return modelMapper.map(matchDto, Match.class);
    }

    public MatchDto toDto(Match match) {
        return modelMapper.map(match, MatchDto.class);
    }
}