package com.example.testpfsentities.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Category category;

    @OneToOne
    private Room room;

    @OneToMany(mappedBy = "matchStats")
    private List<Statistic> statistics;

    @ManyToOne
    private Ground ground;

}
