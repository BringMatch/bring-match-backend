package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.dto.TeamPlayerDto;
import com.example.testpfsentities.entities.Team;
import com.example.testpfsentities.entities.TeamPlayer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class TeamPlayerMapper {
private final ModelMapper modelMapper;

    public List<TeamPlayerDto> toDto(List<TeamPlayer> teamPlayers) {
        return teamPlayers
                .stream()
                .map(element -> modelMapper.map(element, TeamPlayerDto.class))
                .collect(Collectors.toList());
    }

    public List<TeamPlayer> toBo(List<TeamPlayerDto> teamPlayerDtos) {
        return teamPlayerDtos
                .stream()
                .map(element -> modelMapper.map(element, TeamPlayer.class))
                .collect(Collectors.toList());
    }
}
