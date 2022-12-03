package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.entities.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeamMapper {
    private final ModelMapper modelMapper;

    public List<Team> toBo(List<TeamDto> teamDto) {
        return teamDto
                .stream()
                .map(element -> modelMapper.map(element, Team.class))
                .collect(Collectors.toList());
    }

    public Team toBo(TeamDto teamDto) {
        return modelMapper.map(teamDto, Team.class);
    }
}
