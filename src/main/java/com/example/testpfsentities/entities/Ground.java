package com.example.testpfsentities.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PROTECTED)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value = {"matches"},allowSetters = true)
public class Ground extends AbstractEntity {

    @OneToMany(mappedBy = "ground")
    private List<Match> matches;

    @ManyToOne
    private Owner owner;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Reservation> reservations;

    private String name;

    @OneToMany(mappedBy = "ground")
    List<Image> images;

    private String address;


}
