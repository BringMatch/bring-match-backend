package com.example.testpfsentities.entities;


import com.example.testpfsentities.entities.enums.Role;
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
@Entity
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(allowSetters = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends AbstractEntity {
    //
    @Transient
    String password;
    private String firstName;
    private String lastName;
    // the user should connect with his phoneNumber !
    private String phoneNumber;

    //    @Column(nullable = false)
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role roleName;
    private String username;

    @Column(columnDefinition = "boolean default false")
    private boolean active;

    @Enumerated(EnumType.STRING)
    private Role role;
}
