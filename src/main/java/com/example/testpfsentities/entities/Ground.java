package com.example.testpfsentities.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

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

    private String image;
    @OneToMany(mappedBy = "ground")
    private List<Match> matches;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Reservation> reservations;
    @ManyToOne
    private Owner owner;
    @Column(nullable = false, unique = true)
    private String name;
    private String address;

    //@ColumnDefault("boolean default true")
    private boolean open;

    private int startHour;
    private int endHour;
    private String town;

    private String region;


}
