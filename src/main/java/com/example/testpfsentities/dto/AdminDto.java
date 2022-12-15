package com.example.testpfsentities.dto;

import com.example.testpfsentities.entities.NotificationAdmin;
import com.example.testpfsentities.entities.Owner;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto extends UserDto {
    private List<OwnerDto> owners;

    private List<NotificationAdminDto> notificationAdmins;
}
