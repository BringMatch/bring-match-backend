package com.example.testpfsentities.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@MappedSuperclass
public class User {
    @Id
    private Long id;

    private String firstName;
    private String lastName;

    // the user should connect with his phoneNumber !
    private String phoneNumber;   // signIn using phoneNumber for clients

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String roleName;


}
