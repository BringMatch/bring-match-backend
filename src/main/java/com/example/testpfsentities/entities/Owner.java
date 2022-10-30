package com.example.testpfsentities.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Owner extends User{

    @Id
    private Long id;

    @OneToMany
    private List<Ground> grounds;

}
