package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.NotificationPlayerDto;
import com.example.testpfsentities.entities.NotificationPlayer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationPlayerMapper {
    private final ModelMapper modelMapper;

    public NotificationPlayer toBo(NotificationPlayerDto notificationPlayerDto) {
        return modelMapper.map(notificationPlayerDto, NotificationPlayer.class);
    }

    public NotificationPlayerDto toDto(NotificationPlayer notificationPlayer) {
        return modelMapper.map(notificationPlayer, NotificationPlayerDto.class);
    }

    public List<NotificationPlayer> toBo(List<NotificationPlayerDto> notificationPlayerDtoList) {
        return notificationPlayerDtoList
                .stream()
                .map(element -> modelMapper.map(element, NotificationPlayer.class))
                .collect(Collectors.toList());
    }

    public List<NotificationPlayerDto> toDto(List<NotificationPlayer> notificationPlayers) {
        return notificationPlayers
                .stream()
                .map(element -> modelMapper.map(element, NotificationPlayerDto.class))
                .collect(Collectors.toList());
    }
}
