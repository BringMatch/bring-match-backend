package com.example.testpfsentities.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(allowSetters = true)
public class Owner extends User {

    @OneToMany(mappedBy = "owner")
    private List<Ground> grounds;

    @OneToMany(mappedBy = "owner")
    private List<NotificationOwner> notificationPlayer;

    private String num_cin;
    private String num_certif_prop;

    @Column(columnDefinition = "boolean default false")
    private boolean active;

}
