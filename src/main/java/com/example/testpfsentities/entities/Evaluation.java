package com.example.testpfsentities.entities;

import javax.persistence.*;
import java.util.List;

@Embeddable
public class Evaluation {

    private Long evaluation_id;
    private String numberGoals;
    private String numberAssists;
    private Double generalNote;

}
