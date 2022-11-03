package com.example.testpfsentities.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long team_id;

    @ManyToMany(mappedBy = "teams")
    private List<Player> players;

    @ManyToOne
    private Room room;

}
