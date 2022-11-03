package com.example.testpfsentities.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Statistic {
    @Id
    private Long statistic_id;

    @ManyToOne
    private Player playerStats;

    @ManyToOne
    private Match matchStats;

    @Embedded
    private Evaluation evaluation;

}
