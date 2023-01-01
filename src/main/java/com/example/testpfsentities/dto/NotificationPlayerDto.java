package com.example.testpfsentities.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NotificationPlayerDto extends NotificationDto{
    private PlayerDto player;
    private MatchDto match;
}
