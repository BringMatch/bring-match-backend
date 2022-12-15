package com.example.testpfsentities.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NotificationPlayerDto extends NotificationDto{
    private PlayerDto owner_match;
    private String currentMatchId;
}
