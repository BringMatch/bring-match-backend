package com.example.testpfsentities.dto;

import com.example.testpfsentities.entities.Admin;
import com.example.testpfsentities.entities.Owner;
import lombok.*;

import javax.persistence.OneToOne;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NotificationAdminDto extends NotificationDto {
    private AdminDto userTo;

    private OwnerDto userFrom;
}
