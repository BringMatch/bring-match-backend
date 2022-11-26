package com.example.testpfsentities.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Ground {

    @Id
    private Long ground_id;

    @OneToMany(mappedBy = "ground")
    private List<Match> matches;

    private String name;

    @OneToMany(mappedBy = "ground")
    List<Image> images;

    private String address;

    @ManyToOne
    private Owner owner;



}
