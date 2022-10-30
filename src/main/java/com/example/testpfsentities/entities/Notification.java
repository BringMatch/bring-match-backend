package com.example.testpfsentities.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Entity
@MappedSuperclass
public class Notification {

    @Id
    private Long id;

    private Date timestamp;

}
