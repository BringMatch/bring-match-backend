package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.MatchDto;
import com.example.testpfsentities.entities.Match;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class PlayerSearchMapper {
    private final ModelMapper modelMapper;

    public Match toBo(MatchDto matchDto) {
        return modelMapper.map(matchDto, Match.class);
    }

    public MatchDto toDto(Match match) {
        return modelMapper.map(match, MatchDto.class);
    }

    public List<Match> toBo(List<MatchDto> matchDtos) {
        return matchDtos
                .stream()
                .map(element -> modelMapper.map(element, Match.class))
                .collect(Collectors.toList());
    }
    public List<MatchDto> toDto(List<Match> matches) {
        return matches
                .stream()
                .map(element -> modelMapper.map(element, MatchDto.class))
                .collect(Collectors.toList());
    }
}