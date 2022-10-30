package com.example.testpfsentities.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Ground {

    @Id
    private Long id;

    @OneToMany(mappedBy = "ground")
    private List<Match> matches;
}
