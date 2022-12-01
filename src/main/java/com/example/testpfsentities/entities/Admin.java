package com.example.testpfsentities.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(allowSetters = true)
public class Admin extends User {

    @OneToMany(targetEntity = Owner.class, cascade = CascadeType.ALL)
    private List<Owner> owners;

    @OneToMany(mappedBy = "userFrom" , cascade =CascadeType.ALL)
    private List<NotificationAdmin> notificationAdmins;
}
