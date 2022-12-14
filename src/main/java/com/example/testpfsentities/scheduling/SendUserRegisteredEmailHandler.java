package com.example.testpfsentities.scheduling;

import com.example.testpfsentities.mapper.OwnerMapper;
import com.example.testpfsentities.service.AdminService;
import com.example.testpfsentities.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@RequiredArgsConstructor
public class SendUserRegisteredEmailHandler {
    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    @EventListener
    public void handleNewUserEvent(final NewUserEvent newUserEvent) throws IOException {
        //ownerService.save(ownerMapper.toDto(newUserEvent.getUser()));
    }

}
