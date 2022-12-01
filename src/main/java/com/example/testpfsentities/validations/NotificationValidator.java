package com.example.testpfsentities.validations;

import com.example.testpfsentities.service.NotificationAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor()
@Slf4j
public class NotificationValidator {
    private final NotificationAdminService notificationAdminService;
    public boolean checkNotificationsNotEmpty(){
        return true;
    }
}
