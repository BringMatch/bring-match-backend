package com.example.testpfsentities.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Image extends AbstractEntity {
    private String name;
    @ManyToOne
    @JoinTable(name = "ground_images", joinColumns = @JoinColumn(name = "image"),
            inverseJoinColumns = @JoinColumn(name = "ground")
    )
    private Ground ground;


}
