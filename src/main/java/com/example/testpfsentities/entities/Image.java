package com.example.testpfsentities.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne
    @JoinTable(name = "ground_images", joinColumns = @JoinColumn(name = "image"),
            inverseJoinColumns = @JoinColumn(name = "ground")
    )
    private Ground ground;


}
