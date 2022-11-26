package com.example.testpfsentities.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String roleName;

}
