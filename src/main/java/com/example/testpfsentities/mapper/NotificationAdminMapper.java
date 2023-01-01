package com.example.testpfsentities.mapper;

import com.example.testpfsentities.dto.NotificationAdminDto;
import com.example.testpfsentities.dto.OwnerDto;
import com.example.testpfsentities.entities.NotificationAdmin;
import com.example.testpfsentities.entities.Owner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationAdminMapper {
    private final ModelMapper modelMapper;

    public NotificationAdmin toBo(NotificationAdminDto notificationAdminDto) {
        return modelMapper.map(notificationAdminDto, NotificationAdmin.class);
    }

    public NotificationAdminDto toDto(NotificationAdmin notificationAdmin) {
        return modelMapper.map(notificationAdmin, NotificationAdminDto.class);
    }

    public List<NotificationAdmin> toBo(List<NotificationAdminDto> notificationAdminDtos) {
        return notificationAdminDtos
                .stream()
                .map(element -> modelMapper.map(element, NotificationAdmin.class))
                .collect(Collectors.toList());
    }

    public List<NotificationAdminDto> toDto(List<NotificationAdmin> notificationAdmins) {
        return notificationAdmins
                .stream()
                .map(element -> modelMapper.map(element, NotificationAdminDto.class))
                .collect(Collectors.toList());
    }
}
