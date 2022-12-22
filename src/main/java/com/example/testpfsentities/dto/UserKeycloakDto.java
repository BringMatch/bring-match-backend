package com.example.testpfsentities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserKeycloakDto {
    private String userName;
    private String email;
    private String password;
    private String firstname;
    private String lastName;
}
