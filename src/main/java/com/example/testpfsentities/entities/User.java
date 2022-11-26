package com.example.testpfsentities.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties( allowSetters = true)
@Inheritance
public class User extends AbstractEntity {
    private String firstName;
    private String lastName;
    // the user should connect with his phoneNumber !
    private String phoneNumber;   // signIn using phoneNumber for clients
    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String roleName;

}
