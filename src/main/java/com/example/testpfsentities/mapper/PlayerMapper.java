package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.GroundDto;
import com.example.testpfsentities.dto.PlayerDto;
import com.example.testpfsentities.entities.Ground;
import com.example.testpfsentities.entities.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
}
