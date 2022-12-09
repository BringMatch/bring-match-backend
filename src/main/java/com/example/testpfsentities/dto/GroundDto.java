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
    private String owner_id;
    private String match_id;
}
