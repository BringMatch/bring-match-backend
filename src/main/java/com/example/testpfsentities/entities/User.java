package com.example.testpfsentities.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String firstName;
    private String lastName;

    // the user should connect with his phoneNumber !
    private String phoneNumber;   // signin using phoneNumber for clients

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false)
    private String roleName;
}
