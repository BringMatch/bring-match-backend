package com.example.testpfsentities.entities;

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
