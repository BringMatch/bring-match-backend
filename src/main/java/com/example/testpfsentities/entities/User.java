package com.example.testpfsentities.entities;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @CreatedDate
    Instant createdAt;

    @LastModifiedDate
    Instant updatedAt;

}
