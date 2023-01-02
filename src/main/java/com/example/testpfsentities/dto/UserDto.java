package com.example.testpfsentities.dto;

import com.example.testpfsentities.entities.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    private Date createdAt;
    private Date updatedAt;
    private String id;
    @Size(min = 5, message = "min is 5")
    @Size(max = 10, message = "max is 10")
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @Pattern(regexp = "^(.+)@(.+)$",
            message = "you must respect the correct format of email ")
    private String email;

    @Pattern(regexp = "OWNER|ADMIN|PLAYER", message = "you must respect these types  : OWNER , ADMIN , PLAYER")
    private String role;

    private String password;
    private String username;

}
