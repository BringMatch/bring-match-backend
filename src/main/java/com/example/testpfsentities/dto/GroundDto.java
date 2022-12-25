package com.example.testpfsentities.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroundDto {
    private String id;
    private String name;
    private String address;
    private int startHour;
    private int endHour;
    private String town;
    private String region;
    private boolean open;

    private OwnerDto owner;
    private boolean free;
}
