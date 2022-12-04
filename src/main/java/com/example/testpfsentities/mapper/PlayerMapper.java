package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.dto.TeamDto;
import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.entities.Player;
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
public class PlayerMapper {
    private final ModelMapper modelMapper;

    public Player toBo(PlayerDto playerDto) {
        return modelMapper.map(playerDto, Player.class);
    }

    public PlayerDto toDto(Player player) {
        return modelMapper.map(player, PlayerDto.class);
    }

    public List<Player> toBo(List<PlayerDto> playerDto) {
        return playerDto
                .stream()
                .map(element -> modelMapper.map(element, Player.class))
                .collect(Collectors.toList());
    }
    public List<PlayerDto> toDto(List<Player> players) {
        return players
                .stream()
                .map(element -> modelMapper.map(element, PlayerDto.class))
                .collect(Collectors.toList());
    }
}
