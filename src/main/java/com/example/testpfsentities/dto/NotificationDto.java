package com.example.testpfsentities.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private String id;
    private String message;
    private Boolean delivered;
    private Boolean read;
}
