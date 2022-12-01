package com.example.testpfsentities.dto;

import com.example.testpfsentities.entities.enums.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    @Size(min = 5, message = "min is 5")
    @Size(max = 10, message = "max is 10")
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    @Pattern(regexp = "OWNER|ADMIN|PLAYER" ,message = "you must respect these types  : OWNER , ADMIN , PLAYER")
    private String roleName;

    private String userPassword;
}
