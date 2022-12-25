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
@JsonIgnoreProperties(value = {"matches"}, allowSetters = true)
public class Ground extends AbstractEntity {

    @OneToMany(mappedBy = "ground")
    List<Image> images;
    @OneToMany(mappedBy = "ground")
    private List<Match> matches;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Reservation> reservations;
    @ManyToOne
    private Owner owner;
    @Column(nullable = false, unique = true)
    private String name;
    private String address;

    @Column(columnDefinition = "boolean default true")
    private boolean open;

    private int startHour;
    private int endHour;
    private String town;

    @Column(columnDefinition = "boolean default true")
    private boolean free;
    private String region;


}
