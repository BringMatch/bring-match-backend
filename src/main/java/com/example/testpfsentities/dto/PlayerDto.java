package com.example.testpfsentities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDto {
    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String email;
    private String roleName;
    private String town;
    private String region;
    private String team_id;
//    private String match_id;
}
